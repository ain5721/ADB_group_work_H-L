package com.scalardb.demo.service;

import com.google.common.collect.Lists;
import com.scalar.db.api.DistributedTransaction;
import com.scalardb.demo.model.ProductImageModel;
import com.scalardb.demo.vo.ProductImageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ListenYoung
 * @date: Created on 17:12 2023/6/30
 * @modified By:
 */
@Service
public class ProductImageService {
  @Autowired
  private ProductImageModel productImageModel;

  private final static Logger logger = LoggerFactory.getLogger(ProductImageService.class);

  public List<ProductImageVO> listImagesByProductId(String productId) {
    try {
      DistributedTransaction transaction = productImageModel.startTransaction();
      List<ProductImageVO> imageVOList = productImageModel.listImagesByProductId(transaction, productId);
      transaction.commit();
      return imageVOList;
    } catch (Exception e) {
      logger.error("error when list product image by product id, e: " + e);
      return Lists.newArrayList();
    }
  }
}
