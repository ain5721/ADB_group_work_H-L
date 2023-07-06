package com.scalardb.demo.model;

import com.google.common.collect.Lists;
import com.scalar.db.api.*;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.io.Key;
import com.scalardb.demo.enums.ProductCategory;
import com.scalardb.demo.vo.ProductVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 13:28 2023/6/30
 * @modified By:
 */
@Repository
public class ProductModel extends BaseModel {
  private static final String NAMESPACE = "shoppe";
  private static final String TABLE_NAME = "product";
  private static final String ID = "id";

  public List<ProductVO> listByIds(DistributedTransaction transaction, List<String> ids) throws CrudException {
    return listAll(transaction)
        .stream()
        .filter(vo -> ids.contains(vo.id))
        .collect(Collectors.toList());
  }

  public ProductVO findById(DistributedTransaction transaction, String id) throws CrudException {
    Get get =
        Get.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, id))
            .build();
    Optional<Result> result = transaction.get(get);

    return result.map(record -> ProductVO.from(
        record.getText(ID),
        record.getText("name"),
        record.getInt("category"),
        record.getText("title"),
        record.getText("intro"),
        record.getText("image"),
        record.getDouble("price"),
        record.getDouble("selling_price"),
        record.getInt("stock"),
        record.getInt("limit_count"),
        record.getInt("sale_count")
    )).orElse(null);
  }

  public List<ProductVO> listByCategories(DistributedTransaction transaction, List<ProductCategory> categories) throws CrudException {
    return listAll(transaction)
        .stream()
        .filter(vo -> categories.contains(vo.category))
        .collect(Collectors.toList());
  }

  public List<ProductVO> listAll(DistributedTransaction transaction) throws CrudException {
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
          .map(result -> ProductVO.from(
              result.getText(ID),
              result.getText("name"),
              result.getInt("category"),
              result.getText("title"),
              result.getText("intro"),
              result.getText("image"),
              result.getDouble("price"),
              result.getDouble("selling_price"),
              result.getInt("stock"),
              result.getInt("limit_count"),
              result.getInt("sale_count")
          ))
          .filter(vo -> vo.stock > 0)
          .collect(Collectors.toList());
    }
  }

  public void update(DistributedTransaction transaction, ProductVO productVO) throws CrudException {
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, productVO.id))
            .textValue("name", productVO.name)
            .intValue("category", productVO.category.code)
            .textValue("title", productVO.title)
            .textValue("intro", productVO.intro)
            .textValue("image", productVO.image)
            .doubleValue("price", productVO.price.doubleValue())
            .doubleValue("selling_price", productVO.sellingPrice.doubleValue())
            .intValue("stock", productVO.stock)
            .intValue("limit_count", productVO.limitCount)
            .intValue("sale_count", productVO.saleCount)
            .build();
    transaction.put(put);

  }

  @Deprecated
  public void insert(
      String id,
      String name,
      Integer code,
      String title,
      String intro,
      String image,
      Double price,
      Double sellingPrice,
      Integer stock,
      Integer limitCount,
      Integer saleCount
  ) throws TransactionException {
    DistributedTransaction transaction = startTransaction();
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, id))
            .textValue("name", name)
            .intValue("category", code)
            .textValue("title", title)
            .textValue("intro", intro)
            .textValue("image", image)
            .doubleValue("price", price)
            .doubleValue("selling_price", sellingPrice)
            .intValue("stock", stock)
            .intValue("limit_count", limitCount)
            .intValue("sale_count", saleCount)
            .build();
    transaction.put(put);
    transaction.commit();
  }
}
