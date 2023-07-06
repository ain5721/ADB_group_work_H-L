package com.scalardb.demo.model;

import com.google.common.collect.Lists;
import com.scalar.db.api.*;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.io.Key;
import com.scalardb.demo.vo.CartVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 17:43 2023/6/30
 * @modified By:
 */
@Repository
public class CartModel extends BaseModel {
  private static final String NAMESPACE = "shoppe";
  private static final String TABLE_NAME = "cart";
  private static final String ID = "id";

  public List<CartVO> listByUserId(DistributedTransaction transaction, String userId) throws CrudException {
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
          .map(result -> CartVO.from(
              result.getText(ID),
              result.getText("user_id"),
              result.getText("product_id"),
              result.getInt("count")
          ))
          .filter(r -> userId.equals(r.userId))
          .collect(Collectors.toList());
    }
  }

  public DistributedTransaction insert(DistributedTransaction transaction, String userId, String productId, Integer count) throws CrudException {
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, generateId()))
            .textValue("user_id", userId)
            .textValue("product_id", productId)
            .intValue("count", count)
            .build();
    transaction.put(put);
    return transaction;
  }

  public void delete(DistributedTransaction transaction, String id) throws CrudException {
    Get get =
        Get.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, id))
            .build();
    Optional<Result> result = transaction.get(get);

    if (!result.isPresent()) {
      throw new RuntimeException("User not found");
    }

    Delete delete =
        Delete.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, id))
            .build();
    transaction.delete(delete);
  }

  public DistributedTransaction update(DistributedTransaction transaction, String id, String userId, String productId, Integer count) throws CrudException {
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, id))
            .textValue("user_id", userId)
            .textValue("product_id", productId)
            .intValue("count", count)
            .build();
    transaction.put(put);

    return transaction;
  }
}
