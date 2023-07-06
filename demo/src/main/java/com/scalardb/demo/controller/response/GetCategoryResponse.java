package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.scalardb.demo.enums.ResponseCode;

import java.util.List;
import java.util.Map;

/**
 * @author: ListenYoung
 * @date: Created on 16:46 2023/6/30
 * @modified By:
 */
public class GetCategoryResponse {
  public String code;
  @JsonProperty("category")
  public List<ProductCategoryResponse> categories;

  public static GetCategoryResponse from(ResponseCode code, Map<Integer, String> categoryMap) {
    GetCategoryResponse response = new GetCategoryResponse();
    response.code = code.code;
    response.categories = Lists.newArrayList();
    categoryMap.forEach((id, name) -> response.categories.add(ProductCategoryResponse.from(id, name)));

    return response;
  }
}
