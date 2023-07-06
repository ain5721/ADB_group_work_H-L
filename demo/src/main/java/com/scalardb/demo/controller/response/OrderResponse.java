package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.vo.OrderVO;
import com.scalardb.demo.vo.ProductVO;

import java.math.BigDecimal;

/**
 * @author: ListenYoung
 * @date: Created on 14:49 2023/6/30
 * @modified By:
 */
public class OrderResponse {
  @JsonProperty("id")
  public String id;
  @JsonProperty("order_id")
  public String transId;
  @JsonProperty("user_id")
  public String userId;
  @JsonProperty("product_id")
  public String productId;
  @JsonProperty("product_num")
  public Integer count;
  @JsonProperty("product_price")
  public BigDecimal amount;
  @JsonProperty("order_time")
  public Long timeCreated;
  @JsonProperty("product_name")
  public String productName;
  @JsonProperty("product_picture")
  public String productImage;

  public static OrderResponse from(OrderVO orderVO, ProductVO productVO) {
    OrderResponse response = new OrderResponse();
    response.id = orderVO.id;
    response.transId = orderVO.transId;
    response.userId = orderVO.userId;
    response.productId = orderVO.productId;
    response.count = orderVO.count;
    response.amount = orderVO.amount;
    response.timeCreated = orderVO.timeCreated;
    response.productName = productVO.name;
    response.productImage = productVO.image;

    return response;
  }
}
