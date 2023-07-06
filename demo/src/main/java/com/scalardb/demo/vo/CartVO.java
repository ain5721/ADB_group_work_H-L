package com.scalardb.demo.vo;

/**
 * @author: ListenYoung
 * @date: Created on 17:45 2023/6/30
 * @modified By:
 */
public class CartVO {
  public String id;
  public String userId;
  public String productId;
  public Integer count;

  public static CartVO from(String id, String userId, String productId, Integer count) {
    CartVO vo = new CartVO();
    vo.id = id;
    vo.userId = userId;
    vo.productId = productId;
    vo.count = count;

    return vo;
  }
}
