package com.scalardb.demo.controller.response;

import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.vo.ProductVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 13:52 2023/6/30
 * @modified By:
 */
public class GetCollectionListResponse {
  public String code;
  public List<ProductResponse> collectList;

  public static GetCollectionListResponse from(ResponseCode code, List<ProductVO> productVOList) {
    GetCollectionListResponse response = new GetCollectionListResponse();
    response.code = code.code;
    response.collectList = productVOList.stream().map(ProductResponse::from).collect(Collectors.toList());

    return response;
  }

}
