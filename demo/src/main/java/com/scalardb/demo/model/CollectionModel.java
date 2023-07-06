package com.scalardb.demo.model;

import com.google.common.collect.Lists;
import com.scalar.db.api.*;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.io.Key;
import com.scalardb.demo.vo.CollectionVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import utils.TimeUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 12:30 2023/6/30
 * @modified By:
 */
@Repository
public class CollectionModel extends BaseModel {
  private static final String NAMESPACE = "shoppe";
  private static final String TABLE_NAME = "collection";
  private static final String ID = "id";

  public DistributedTransaction insert(DistributedTransaction transaction, String userId, String productId) throws CrudException {
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, generateId()))
            .textValue("user_id", userId)
            .textValue("product_id", productId)
            .bigIntValue("time_created", TimeUtils.now())
            .build();
    transaction.put(put);
    return transaction;
  }

  public List<CollectionVO> listByUserId(DistributedTransaction transaction, String userId) throws CrudException {
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
          .map(result -> CollectionVO.from(result.getText(ID), result.getText("user_id"), result.getText("product_id"), result.getBigInt("time_created")))
          .filter(vo -> userId.equals(vo.userId))
          .collect(Collectors.toList());
    }
  }

  public CollectionVO findByUserIdAndProductId(DistributedTransaction transaction, String userId, String productId) throws CrudException {
    List<CollectionVO> collectionVOList = listByUserId(transaction, userId);
    if (CollectionUtils.isEmpty(collectionVOList)) {
      return null;
    } else {
      return collectionVOList.stream().filter(vo -> productId.equals(vo.productId)).findFirst().orElse(null);
    }
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
      throw new RuntimeException("collection not found");
    }

    Delete delete =
        Delete.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, id))
            .build();
    transaction.delete(delete);
  }
}
