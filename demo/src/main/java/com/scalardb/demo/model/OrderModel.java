package com.scalardb.demo.model;

import com.google.common.collect.Lists;
import com.scalar.db.api.DistributedTransaction;
import com.scalar.db.api.Put;
import com.scalar.db.api.Result;
import com.scalar.db.api.Scan;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.io.Key;
import com.scalardb.demo.vo.OrderVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import utils.TimeUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 14:26 2023/6/30
 * @modified By:
 */
@Repository
public class OrderModel extends BaseModel {
  private static final String NAMESPACE = "shoppe";
  private static final String TABLE_NAME = "order";
  private static final String ID = "id";

  public List<OrderVO> listByUserId(DistributedTransaction transaction, String userId) throws CrudException {
    Scan scan =
        Scan.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .all()
            .build();

    List<Result> results = transaction.scan(scan);

    if (CollectionUtils.isEmpty(results)) {
      return Lists.newArrayList();
    } else {
      return results.stream()
          .map(result -> OrderVO.from(
              result.getText(ID),
              result.getText("trans_id"),
              result.getText("user_id"),
              result.getText("product_id"),
              result.getInt("count"),
              result.getDouble("amount"),
              result.getBigInt("time_created")
          ))
          .filter(vo -> userId.equals(vo.userId))
          .collect(Collectors.toList());
    }
  }

  public void batchInsert(DistributedTransaction transaction, List<OrderVO> orders) {
    String transId = generateId();
    orders.forEach(order -> {
      try {
        insert(transaction, transId, order.userId, order.productId, order.count, order.amount);
      } catch (CrudException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void insert(DistributedTransaction transaction, String transId, String userId, String productId, Integer count, BigDecimal amount) throws CrudException {
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, generateId()))
            .textValue("trans_id", transId)
            .textValue("user_id", userId)
            .textValue("product_id", productId)
            .intValue("count", count)
            .doubleValue("amount", amount.doubleValue())
            .bigIntValue("time_created", TimeUtils.now())
            .build();
    transaction.put(put);
  }
}
