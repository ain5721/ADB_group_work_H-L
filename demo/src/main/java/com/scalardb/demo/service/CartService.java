package com.scalardb.demo.service;

import com.google.common.collect.Lists;
import com.scalar.db.api.DistributedTransaction;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.model.CartModel;
import com.scalardb.demo.model.ProductModel;
import com.scalardb.demo.vo.CartVO;
import com.scalardb.demo.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: ListenYoung
 * @date: Created on 17:49 2023/6/30
 * @modified By:
 */
@Service
public class CartService {
  @Autowired
  private CartModel cartModel;
  @Autowired
  private ProductModel productModel;

  private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

  public List<CartVO> listByUserId(String userId) {
    try {
      DistributedTransaction transaction = cartModel.startTransaction();
      List<CartVO> cartVOList = cartModel.listByUserId(transaction, userId);
      transaction.commit();
      return cartVOList;
    } catch (Exception e) {
      logger.error("error when list cart by userId, e: " + e);
      return Lists.newArrayList();
    }
  }

  public ResponseCode insertOrUpdate(String userId, String productId, Integer count) {
    try {
      DistributedTransaction transaction = cartModel.startTransaction();
      List<CartVO> cartVOList = cartModel.listByUserId(transaction, userId);

      if (!CollectionUtils.isEmpty(cartVOList)) {
        CartVO repeatedItem = cartVOList.stream().filter(vo -> productId.equals(vo.productId)).findFirst().orElse(null);
        if (repeatedItem != null) {
          transaction.abort();
          ResponseCode responseCode = update(userId, productId, count + repeatedItem.count);
          if (responseCode == ResponseCode.COMMON_SUCCESS) {
            return ResponseCode.UPDATE_CART_SUCCESS;
          } else {
            return responseCode;
          }
        }
      }

      transaction = cartModel.insert(transaction, userId, productId, count);
      transaction.commit();
      return ResponseCode.COMMON_SUCCESS;
    } catch (Exception e) {
      logger.error("error when insert cart, e: " + e);
      return ResponseCode.COMMON_FAILED;
    }
  }

  public ResponseCode update(String userId, String productId, Integer count) {
    try {
      DistributedTransaction transaction = cartModel.startTransaction();
      List<CartVO> cartVOList = cartModel.listByUserId(transaction, userId);

      if (CollectionUtils.isEmpty(cartVOList)) {
        transaction = cartModel.insert(transaction, userId, productId, count);
        transaction.commit();
        return ResponseCode.COMMON_SUCCESS;
      }

      CartVO repeatedItem = cartVOList.stream().filter(vo -> productId.equals(vo.productId)).findFirst().orElse(null);
      if (repeatedItem == null) {
        transaction = cartModel.insert(transaction, userId, productId, count);
        transaction.commit();
        return ResponseCode.COMMON_SUCCESS;
      }

      ProductVO productVO = productModel.findById(transaction, repeatedItem.productId);
      if (count > productVO.limitCount || count < 0) {
        transaction.abort();
        return ResponseCode.OVER_LIMIT;
      } else {
        transaction = cartModel.update(
            transaction,
            repeatedItem.id,
            repeatedItem.userId,
            repeatedItem.productId,
            count
            );
        transaction.commit();
        return ResponseCode.COMMON_SUCCESS;
      }
    } catch (Exception e) {
      logger.error("error when update cart, e: " + e);
      return ResponseCode.COMMON_FAILED;
    }
  }

  public ResponseCode delete(String userId, String productId) {
    try {
      DistributedTransaction transaction = cartModel.startTransaction();
      List<CartVO> cartVOList = cartModel.listByUserId(transaction, userId);
      if (CollectionUtils.isEmpty(cartVOList)) {
        transaction.abort();
        return ResponseCode.COMMON_SUCCESS;
      }

      CartVO targetItem = cartVOList.stream().filter(vo -> productId.equals(vo.productId)).findFirst().orElse(null);
      if (targetItem == null) {
        transaction.abort();
        return ResponseCode.COMMON_SUCCESS;
      }

      cartModel.delete(transaction, targetItem.id);
      transaction.commit();
      return ResponseCode.COMMON_SUCCESS;
    } catch (Exception e) {
      logger.error("error when delete cart, e: " + e);
      return ResponseCode.COMMON_FAILED;
    }
  }
}
