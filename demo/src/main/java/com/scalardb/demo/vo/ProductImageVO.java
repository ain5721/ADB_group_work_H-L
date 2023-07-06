package com.scalardb.demo.vo;

/**
 * @author: ListenYoung
 * @date: Created on 17:07 2023/6/30
 * @modified By:
 */
public class ProductImageVO {
  public String id;
  public String productId;
  public String path;
  public String intro;

  public static ProductImageVO from(String id, String productId, String path, String intro) {
    ProductImageVO vo = new ProductImageVO();
    vo.id = id;
    vo.productId = productId;
    vo.path = path;
    vo.intro = intro;

    return vo;
  }
}
