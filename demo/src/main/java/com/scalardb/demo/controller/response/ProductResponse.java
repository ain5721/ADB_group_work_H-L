package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.vo.ProductVO;

import java.math.BigDecimal;

/**
 * @author: ListenYoung
 * @date: Created on 13:53 2023/6/30
 * @modified By:
 */
public class ProductResponse {
  @JsonProperty("product_id")
  public String id;
  @JsonProperty("product_name")
  public String name;
  @JsonProperty("category_id")
  public Integer categoryId;
  @JsonProperty("product_title")
  public String title;
  @JsonProperty("product_intro")
  public String intro;
  @JsonProperty("product_picture")
  public String image;
  @JsonProperty("product_price")
  public BigDecimal price;
  @JsonProperty("product_selling_price")
  public BigDecimal sellingPrice;
  @JsonProperty("product_num")
  public Integer limitCount;
  @JsonProperty("product_sales")
  public Integer saleCount;

  public static ProductResponse from(ProductVO vo) {
    ProductResponse response = new ProductResponse();
    response.id = vo.id;
    response.name = vo.name;
    response.categoryId = vo.category.code;
    response.title = vo.title;
    response.intro = vo.intro;
    response.image = vo.image;
    response.price = vo.price;
    response.sellingPrice = vo.sellingPrice;
    response.limitCount = vo.limitCount;
    response.saleCount = vo.saleCount;

    return response;
  }
}
