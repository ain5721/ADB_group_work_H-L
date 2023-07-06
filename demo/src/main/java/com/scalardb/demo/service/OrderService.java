package com.scalardb.demo.service;

import com.google.common.collect.Lists;
import com.scalar.db.api.DistributedTransaction;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.model.CartModel;
import com.scalardb.demo.model.OrderModel;
import com.scalardb.demo.model.ProductModel;
import com.scalardb.demo.vo.CartVO;
import com.scalardb.demo.vo.OrderVO;
import com.scalardb.demo.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 14:38 2023/6/30
 * @modified By:
 */
@Service
public class OrderService {
  @Autowired
  private OrderModel orderModel;
  @Autowired
  private ProductModel productModel;
  @Autowired
  private CartModel cartModel;

  private final static Logger logger = LoggerFactory.getLogger(OrderService.class);

  public List<OrderVO> listByUserId(String userId) {
    try {
      DistributedTransaction transaction = orderModel.startTransaction();
      List<OrderVO> orderVOList = orderModel.listByUserId(transaction, userId);
      transaction.commit();
      return orderVOList;
    } catch (Exception e) {
      logger.error("error when list order by user id, e: " + e);
      return Lists.newArrayList();
    }
  }

  public ResponseCode createOrders(List<OrderVO> orders) throws TransactionException {
    if (CollectionUtils.isEmpty(orders)) {
      return ResponseCode.COMMON_SUCCESS;
    }
    DistributedTransaction transaction = orderModel.startTransaction();

    List<String> productIds = orders.stream().map(o -> o.productId).collect(Collectors.toList());
    Map<String, ProductVO> productMap = productModel
        .listByIds(transaction, productIds)
        .stream()
        .collect(Collectors.toMap(r -> r.id, r -> r, (r1, r2) -> r2));

    // check if settle count is bigger than stack
    long errorCount = orders
        .stream()
        .filter(order -> order.count > productMap.get(order.productId).stock)
        .count();
    if (errorCount > 0) {
      transaction.abort();
      return ResponseCode.OVER_LIMIT;
    }

    //check if orders are all within the cart
    Map<String, CartVO> cartMap = cartModel
        .listByUserId(transaction, orders.get(0).userId)
        .stream()
        .collect(Collectors.toMap(r -> r.productId, r -> r, (r1, r2) -> r2));

    errorCount = orders
        .stream()
        .filter(order -> order.count > cartMap.get(order.productId).count)
        .count();
    if (errorCount > 0) {
      transaction.abort();
      return ResponseCode.OVER_LIMIT;
    }

    //change product stock
    //batch of scalardb?
    orders.forEach(order -> {
      ProductVO productVO = productMap.get(order.productId);
      productVO.saleCount += order.count;
      productVO.stock -= order.count;
      try {
        productModel.update(transaction, productVO);
      } catch (CrudException e) {
        throw new RuntimeException(e);
      }
    });

    //change cart
    //batch of scalardb?
    orders.forEach(order -> {
      CartVO cartVO = cartMap.get(order.productId);
      try {
        if (cartVO.count.equals(order.count)) {
          cartModel.delete(transaction, cartVO.id);
        } else {
          cartModel.update(transaction, cartVO.id, cartVO.userId, cartVO.productId, cartVO.count - order.count);
        }
      } catch (CrudException e) {
        throw new RuntimeException(e);
      }
    });

    //create orders
    orders.forEach(order -> order.amount = productMap.get(order.productId).sellingPrice);
    orderModel.batchInsert(transaction, orders);
    transaction.commit();
    return ResponseCode.COMMON_SUCCESS;
  }
}
