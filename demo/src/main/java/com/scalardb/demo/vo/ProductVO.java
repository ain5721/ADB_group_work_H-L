package com.scalardb.demo.vo;

import com.scalardb.demo.enums.ProductCategory;

import java.math.BigDecimal;

/**
 * @author: ListenYoung
 * @date: Created on 13:30 2023/6/30
 * @modified By:
 */
public class ProductVO {
  public String id;
  public String name;
  public ProductCategory category;
  public String title;
  public String intro;
  public String image;
  public BigDecimal price;
  public BigDecimal sellingPrice;
  public Integer stock;
  public Integer limitCount;
  public Integer saleCount;

  public static ProductVO from(
      String id,
      String name,
      Integer categoryId,
      String title,
      String intro,
      String image,
      Double price,
      Double sellingPrice,
      Integer stock,
      Integer limitCount,
      Integer saleCount
  ) {
    ProductVO vo = new ProductVO();
    vo.id = id;
    vo.name = name;
    vo.category = ProductCategory.fromCode(categoryId);
    vo.title = title;
    vo.intro = intro;
    vo.image = image;
    vo.price = BigDecimal.valueOf(price);
    vo.sellingPrice = BigDecimal.valueOf(sellingPrice);
    vo.stock = stock;
    vo.limitCount = limitCount;
    vo.saleCount = saleCount;

    return vo;
  }
}
