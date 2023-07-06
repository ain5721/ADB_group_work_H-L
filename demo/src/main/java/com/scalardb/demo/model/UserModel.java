package com.scalardb.demo.model;

import com.google.common.collect.Lists;
import com.scalar.db.api.*;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.io.Key;
import com.scalardb.demo.vo.UserVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 20:01 2023/6/22
 * @modified By:
 */
@Repository
public class UserModel extends BaseModel{
  private static final String NAMESPACE = "shoppe";
  private static final String TABLE_NAME = "user";
  private static final String ID = "id";

  public UserVO findByNameAndPassword(DistributedTransaction transaction, String name, String password) throws CrudException {
    List<UserVO> userVOList = listAll(transaction);
    if (CollectionUtils.isEmpty(userVOList)) {
      return null;
    }

    return userVOList
        .stream()
        .filter(vo -> name.equals(vo.name))
        .filter(vo -> password.equals(vo.password))
        .findFirst().orElse(null);
  }
  public List<UserVO> listAll(DistributedTransaction transaction) throws CrudException {
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
          .map(result -> UserVO.from(result.getText(ID), result.getText("name"), result.getText("password")))
          .collect(Collectors.toList());
    }
  }

  public Long countName(DistributedTransaction transaction, String name) throws CrudException {
    return listAll(transaction)
        .stream()
        .filter(user -> name.equals(user.name))
        .count();
  }

  public DistributedTransaction insert(DistributedTransaction transaction, String name, String password) throws CrudException {
    Put put =
        Put.newBuilder()
            .namespace(NAMESPACE)
            .table(TABLE_NAME)
            .partitionKey(Key.ofText(ID, generateId()))
            .textValue("name", name)
            .textValue("password", password)
            .build();
    transaction.put(put);
    return transaction;
  }
}
