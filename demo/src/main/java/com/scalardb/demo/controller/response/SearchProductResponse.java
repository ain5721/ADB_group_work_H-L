package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.vo.ProductVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 15:43 2023/6/30
 * @modified By:
 */
public class SearchProductResponse {
  public String code;
  public Integer total;
  @JsonProperty("Product")
  public List<ProductResponse> products;

  public static SearchProductResponse from(ResponseCode code, Integer total, List<ProductVO> productVOList) {
    SearchProductResponse response = new SearchProductResponse();
    response.code = code.code;
    response.total = total;
    response.products = productVOList.stream().map(ProductResponse::from).collect(Collectors.toList());

    return response;
  }
}
