package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.vo.ProductImageVO;

/**
 * @author: ListenYoung
 * @date: Created on 17:17 2023/6/30
 * @modified By:
 */
public class ProductImageResponse {
  public String id;
  @JsonProperty("product_id")
  public String productId;
  @JsonProperty("product_picture")
  public String path;
  public String intro;

  public static ProductImageResponse from(ProductImageVO vo) {
    ProductImageResponse response = new ProductImageResponse();
    response.id = vo.id;
    response.productId = vo.productId;
    response.path = vo.path;
    response.intro = vo.intro;

    return response;
  }
}
