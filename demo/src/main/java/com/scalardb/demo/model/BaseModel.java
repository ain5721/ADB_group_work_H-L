package com.scalardb.demo.model;

import com.scalar.db.api.DistributedTransaction;
import com.scalar.db.api.DistributedTransactionManager;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.service.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: ListenYoung
 * @date: Created on 20:08 2023/6/22
 * @modified By:
 */
@Repository
public class BaseModel {
  private final DistributedTransactionManager manager;

  private static final String scalarDBProperties = System.getProperty("user.dir") + File.separator + "scalardb.properties";

  @Autowired
  public BaseModel() {
    TransactionFactory factory;
    try {
      factory = TransactionFactory.create(scalarDBProperties);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    manager = factory.getTransactionManager();
  }

  public DistributedTransaction startTransaction() throws TransactionException {
    return manager.start();
  }

  protected String generateId() {
    return UUID.randomUUID().toString();
  }

  @PreDestroy
  public void close() {
    manager.close();
  }
}
