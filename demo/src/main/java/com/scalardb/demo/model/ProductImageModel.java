package com.scalardb.demo.model;

import com.google.common.collect.Lists;
import com.scalar.db.api.DistributedTransaction;
import com.scalar.db.api.Put;
import com.scalar.db.api.Result;
import com.scalar.db.api.Scan;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.io.Key;
import com.scalardb.demo.vo.ProductImageVO;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 17:06 2023/6/30
 * @modified By:
 */
@Repository
public class ProductImageModel extends BaseModel {
  private static final String NAMESPACE = "shoppe";
  private static final String TABLE_NAME = "product_image";
  private static final String ID = "id";

  public List<ProductImageVO> listImagesByProductId(DistributedTransaction transaction, String productId) throws CrudException {
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
          .map(result -> ProductImageVO.from(result.getText(ID), result.getText("product_id"), result.getText("path"), result.getText("intro")))
          .filter(vo -> productId.equals(vo.productId))
          .collect(Collectors.toList());
    }
  }

  @Deprecated
  public void insert(String id, String productId, String path, String intro) throws TransactionException {
    DistributedTransaction transaction = startTransaction();
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, id))
            .textValue("product_id", productId)
            .textValue("path", path)
            .textValue("intro", intro)
            .build();
    transaction.put(put);
    transaction.commit();
  }
}
