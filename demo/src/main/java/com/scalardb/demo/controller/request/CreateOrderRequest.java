package com.scalardb.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author: ListenYoung
 * @date: Created on 12:57 2023/6/30
 * @modified By:
 */
public class CreateOrderRequest {
  @JsonProperty("productID")
  public String productId;
  @JsonProperty("productName")
  public String productName;
  @JsonProperty("productImg")
  public String productImage;
  @JsonProperty("price")
  public BigDecimal amount;
  @JsonProperty("num")
  public Integer count;
  @JsonProperty("maxNum")
  public Integer maxCount;
  @JsonProperty("check")
  public Boolean check;



}
