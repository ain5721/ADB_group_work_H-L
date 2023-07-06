package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: ListenYoung
 * @date: Created on 16:45 2023/6/30
 * @modified By:
 */
public class ProductCategoryResponse {
  @JsonProperty("category_id")
  public Integer code;
  @JsonProperty("category_name")
  public String desc;

  public static ProductCategoryResponse from(Integer code, String desc) {
    ProductCategoryResponse response = new ProductCategoryResponse();
    response.code = code;
    response.desc = desc;

    return response;
  }
}
