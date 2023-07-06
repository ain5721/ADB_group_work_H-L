package com.scalardb.demo.service;

import com.google.common.collect.Lists;
import com.scalar.db.api.DistributedTransaction;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.model.CollectionModel;
import com.scalardb.demo.vo.CollectionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ListenYoung
 * @date: Created on 12:51 2023/6/30
 * @modified By:
 */
@Service
public class CollectionService {
  @Autowired
  private CollectionModel collectionModel;

  private final static Logger logger = LoggerFactory.getLogger(CollectionService.class);

  public ResponseCode insert(String userId, String productId) {
    try {
      DistributedTransaction transaction = collectionModel.startTransaction();
      CollectionVO collectionVO = collectionModel.findByUserIdAndProductId(transaction, userId, productId);
      if (collectionVO != null) {
        //exist, directly success
        transaction.abort();
        return ResponseCode.COMMON_SUCCESS;
      }

      transaction = collectionModel.insert(transaction, userId, productId);
      transaction.commit();
      return ResponseCode.COMMON_SUCCESS;
    } catch (Exception e) {
      logger.error("error when insert collection, e: " + e);
      return ResponseCode.COMMON_FAILED;
    }
  }

  public List<CollectionVO> listByUserId(String userId) {
    try {
      DistributedTransaction transaction = collectionModel.startTransaction();
      List<CollectionVO> collectionVOList = collectionModel.listByUserId(transaction, userId);
      transaction.commit();
      return collectionVOList;
    } catch (Exception e) {
      logger.error("error when list collection by user id, e: " + e);
      return Lists.newArrayList();
    }
  }

  public ResponseCode deleteByUserIdAndProductId(String userId, String productId) {
    try {
      DistributedTransaction transaction = collectionModel.startTransaction();
      CollectionVO collectionVO = collectionModel.findByUserIdAndProductId(transaction, userId, productId);
      if (collectionVO == null) {
        transaction.abort();
        return ResponseCode.COMMON_SUCCESS;
      } else {
        collectionModel.delete(transaction, collectionVO.id);
        transaction.commit();
        return ResponseCode.COMMON_SUCCESS;
      }
    } catch (Exception e) {
      logger.error("error when delete collection, e: " + e);
      return ResponseCode.COMMON_FAILED;
    }
  }
}
