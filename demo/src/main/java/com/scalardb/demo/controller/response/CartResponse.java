package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.vo.CartVO;
import com.scalardb.demo.vo.ProductVO;

import java.math.BigDecimal;

/**
 * @author: ListenYoung
 * @date: Created on 17:52 2023/6/30
 * @modified By:
 */
public class CartResponse {
  public String id;
  @JsonProperty("productID")
  public String productId;
  public String productName;
  @JsonProperty("productImg")
  public String productImage;
  public BigDecimal price;
  @JsonProperty("num")
  public Integer count;
  @JsonProperty("maxNum")
  public Integer limitCount;
  public Boolean check = false;

  public static CartResponse from(CartVO cartVO, ProductVO productVO) {
    CartResponse response = new CartResponse();
    response.id = cartVO.id;
    response.productId = cartVO.productId;
    response.productName = productVO.name;
    response.productImage = productVO.image;
    response.price = productVO.sellingPrice;
    response.count = cartVO.count;
    response.limitCount = productVO.limitCount;

    return response;
  }
}
