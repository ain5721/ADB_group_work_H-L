package com.scalardb.demo.controller;

import com.google.common.collect.Lists;
import com.scalardb.demo.controller.request.CreateOrderListRequest;
import com.scalardb.demo.controller.request.GetOrderRequest;
import com.scalardb.demo.controller.response.CommonResponse;
import com.scalardb.demo.controller.response.GetOrderListResponse;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.service.OrderService;
import com.scalardb.demo.service.ProductService;
import com.scalardb.demo.vo.OrderVO;
import com.scalardb.demo.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 14:41 2023/6/30
 * @modified By:
 */
@RestController
@RequestMapping("/api/user/order")
public class OrderController {
  @Autowired
  private OrderService orderService;
  @Autowired
  private ProductService productService;

  private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

  @PostMapping("/getOrder")
  public GetOrderListResponse listCollectedProductsByUserId(@RequestBody @Valid GetOrderRequest request) {
    List<OrderVO> orderVOList = orderService.listByUserId(request.userId);
    List<ProductVO> productVOList = orderVOList.isEmpty()
        ? Lists.newArrayList()
        : productService.listByIds(orderVOList.stream().map(vo -> vo.productId).collect(Collectors.toList()));

    return GetOrderListResponse.from(ResponseCode.COMMON_SUCCESS, orderVOList, productVOList);
  }

  @PostMapping("/addOrder")
  public CommonResponse create(@RequestBody @Valid CreateOrderListRequest request) {
    if (CollectionUtils.isEmpty(request.orders)) {
      return CommonResponse.from(ResponseCode.COMMON_SUCCESS);
    }
    List<OrderVO> orderVOList = request.orders
        .stream()
        .filter(order -> order.check && order.count <= order.maxCount)
        .map(order -> OrderVO.from(null, null, request.userId, order.productId, order.count, order.amount.doubleValue(), null))
        .collect(Collectors.toList());

    try {
      ResponseCode code = orderService.createOrders(orderVOList);
      return CommonResponse.from(code);
    } catch (Exception e) {
      logger.error("error when batch insert order, e:" + e);
      return CommonResponse.from(ResponseCode.COMMON_FAILED);
    }
  }
}
