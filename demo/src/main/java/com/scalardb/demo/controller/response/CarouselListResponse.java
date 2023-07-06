package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.vo.CarouselVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 17:36 2023/6/30
 * @modified By:
 */
public class CarouselListResponse {
  public String code;
  @JsonProperty("carousel")
  public List<CarouselResponse> carousels;

  public static CarouselListResponse from(ResponseCode code, List<CarouselVO> carouselVOList) {
    CarouselListResponse response = new CarouselListResponse();
    response.code = code.code;
    response.carousels = carouselVOList.stream().map(CarouselResponse::from).collect(Collectors.toList());

    return response;
  }
}
