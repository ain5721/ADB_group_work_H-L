package com.scalardb.demo.vo;

import java.math.BigDecimal;

/**
 * @author: ListenYoung
 * @date: Created on 14:30 2023/6/30
 * @modified By:
 */
public class OrderVO {
  public String id;
  public String transId;
  public String userId;
  public String productId;
  public Integer count;
  public BigDecimal amount;
  public Long timeCreated;

  public static OrderVO from(
      String id,
      String transId,
      String userId,
      String productId,
      Integer count,
      Double amount,
      Long timeCreated
  ) {
    OrderVO vo = new OrderVO();
    vo.id = id;vo.transId = transId;
    vo.userId = userId;
    vo.productId = productId;
    vo.count = count;
    vo.amount = BigDecimal.valueOf(amount);
    vo.timeCreated = timeCreated;

    return vo;
  }
}
