package com.scalardb.demo.service;

import com.scalar.db.api.DistributedTransaction;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.model.UserModel;
import com.scalardb.demo.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ListenYoung
 * @date: Created on 20:38 2023/6/22
 * @modified By:
 */
@Service
public class UserService {
  @Autowired
  private UserModel userModel;

  private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

  public UserVO findByNameAndPassword(String name, String password) {
    try {
      DistributedTransaction transaction = userModel.startTransaction();
      UserVO userVO = userModel.findByNameAndPassword(transaction, name, password);
      transaction.commit();
      return userVO;
    } catch (Exception e) {
      logger.error("error when find user by name and password, e: " + e);
      return null;
    }
  }

  public ResponseCode checkUserNameExistOrNot(String name) {
    try {
      DistributedTransaction transaction = userModel.startTransaction();
      Long count = userModel.countName(transaction, name);
      ResponseCode responseCode = count > 0 ? ResponseCode.USER_NAME_EXIST : ResponseCode.COMMON_SUCCESS;
      transaction.commit();
      return responseCode;
    } catch (Exception e) {
      logger.error("error when check if user name exists, e: " + e);
      return ResponseCode.USER_NAME_EXIST;
    }
  }

  public ResponseCode insert(String name, String password) {
    try {
      DistributedTransaction transaction = userModel.startTransaction();
      Long count = userModel.countName(transaction, name);
      if (count > 0) {
        transaction.abort();
        return ResponseCode.COMMON_FAILED;
      }
      userModel.insert(transaction, name, password);
      transaction.commit();
      return ResponseCode.COMMON_SUCCESS;
    } catch (Exception e) {
      logger.error("error when insert user, e: " + e);
      return ResponseCode.COMMON_FAILED;
    }
  }
}
