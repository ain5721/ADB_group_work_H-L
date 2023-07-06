package com.scalardb.demo.vo;

/**
 * @author: ListenYoung
 * @date: Created on 13:10 2023/6/30
 * @modified By:
 */
public class CollectionVO {
  public String id;
  public String userId;
  public String productId;
  public Long timeCreated;

  public static CollectionVO from(String id, String userId, String productId, Long timeCreated) {
    CollectionVO vo = new CollectionVO();
    vo.id = id;
    vo.userId = userId;
    vo.productId = productId;
    vo.timeCreated = timeCreated;

    return vo;
  }
}
