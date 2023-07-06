package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.vo.CarouselVO;

/**
 * @author: ListenYoung
 * @date: Created on 17:34 2023/6/30
 * @modified By:
 */
public class CarouselResponse {
  @JsonProperty("carousel_id")
  public String id;
  @JsonProperty("imgPath")
  public String path;
  @JsonProperty("describes")
  public String desc;

  public static CarouselResponse from(CarouselVO carouselVO) {
    CarouselResponse response = new CarouselResponse();
    response.id = carouselVO.id;
    response.path = carouselVO.path;
    response.desc = carouselVO.desc;

    return response;
  }
}
