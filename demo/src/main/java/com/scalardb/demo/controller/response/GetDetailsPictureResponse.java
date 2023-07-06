package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.vo.ProductImageVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 17:20 2023/6/30
 * @modified By:
 */
public class GetDetailsPictureResponse {
  public String code;
  @JsonProperty("ProductPicture")
  public List<ProductImageResponse> images;

  public static GetDetailsPictureResponse from(ResponseCode code, List<ProductImageVO> imageVOList) {
    GetDetailsPictureResponse response = new GetDetailsPictureResponse();
    response.code = code.code;
    response.images = imageVOList.stream().map(ProductImageResponse::from).collect(Collectors.toList());

    return response;
  }
}
