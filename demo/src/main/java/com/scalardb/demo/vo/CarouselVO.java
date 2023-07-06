package com.scalardb.demo.vo;

/**
 * @author: ListenYoung
 * @date: Created on 17:25 2023/6/30
 * @modified By:
 */
public class CarouselVO {
  public String id;
  public String path;
  public String desc;

  public static CarouselVO from(String id, String path, String desc) {
    CarouselVO carouselVO = new CarouselVO();
    carouselVO.id = id;
    carouselVO.path = path;
    carouselVO.desc = desc;

    return carouselVO;
  }
}
