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
public class GetHotProductResponse {
  public String code;
  @JsonProperty("Product")
  public List<ProductResponse> products;

  public static GetHotProductResponse from(ResponseCode code, List<ProductVO> productVOList) {
    GetHotProductResponse response = new GetHotProductResponse();
    response.code = code.code;
    response.products = productVOList.stream().map(ProductResponse::from).collect(Collectors.toList());

    return response;
  }
}
