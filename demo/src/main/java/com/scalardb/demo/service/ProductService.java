package com.scalardb.demo.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.scalar.db.api.DistributedTransaction;
import com.scalardb.demo.enums.ProductCategory;
import com.scalardb.demo.model.ProductModel;
import com.scalardb.demo.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 13:49 2023/6/30
 * @modified By:
 */
@Service
public class ProductService {
  @Autowired
  private ProductModel productModel;

  private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

  public List<ProductVO> listByIds(List<String> ids) {
    try {
      DistributedTransaction transaction = productModel.startTransaction();
      List<ProductVO> productVOList = productModel.listByIds(transaction, ids);
      transaction.commit();
      return productVOList;
    } catch (Exception e) {
      logger.error("error when list product by id, e: " + e);
      return Lists.newArrayList();
    }
  }

  public List<ProductVO> listByCategories(List<ProductCategory> categories) {
    try {
      DistributedTransaction transaction = productModel.startTransaction();
      List<ProductVO> productVOList = productModel.listByCategories(transaction, categories);
      transaction.commit();
      return productVOList;
    } catch (Exception e) {
      logger.error("error when list product by category, e: " + e);
      return Lists.newArrayList();
    }
  }

  public List<ProductVO> listAll() {
    try {
      DistributedTransaction transaction = productModel.startTransaction();
      List<ProductVO> productVOList = productModel.listAll(transaction);
      transaction.commit();
      return productVOList;
    } catch (Exception e) {
      logger.error("error when list all product, e: " + e);
      return Lists.newArrayList();
    }
  }

  public Map<Integer, String> getCategoriesInfo() {
    return Arrays.stream(ProductCategory.values()).collect(Collectors.toMap(r -> r.code, r -> r.desc));
  }

  public ProductVO findById(String id) {
    try {
      DistributedTransaction transaction = productModel.startTransaction();
      ProductVO productVO = productModel.findById(transaction, id);
      transaction.commit();
      return productVO;
    } catch (Exception e) {
      logger.error("error when find product by id, e: " + e);
      return null;
    }
  }

  public List<ProductVO> search(String keyword) {
    try {
      DistributedTransaction transaction = productModel.startTransaction();
      List<ProductVO> productVOList = productModel.listAll(transaction);
      transaction.commit();

      Set<ProductVO> resultSet = Sets.newHashSet();
      productVOList.forEach(product -> {
        if (product.name.contains(keyword)) {
          resultSet.add(product);
        }
        if (product.title.contains(keyword)) {
          resultSet.add(product);
        }
        if (product.intro.contains(keyword)) {
          resultSet.add(product);
        }
      });

      return Lists.newArrayList(resultSet);
    } catch (Exception e) {
      logger.error("error when search product, e: " + e);
      return null;
    }
  }
}
