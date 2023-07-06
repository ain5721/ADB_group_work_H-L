package com.scalardb.demo.service;

import com.google.common.collect.Lists;
import com.scalar.db.api.DistributedTransaction;
import com.scalardb.demo.model.CarouselModel;
import com.scalardb.demo.vo.CarouselVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ListenYoung
 * @date: Created on 17:28 2023/6/30
 * @modified By:
 */
@Service
public class StaticResourceService {
  @Autowired
  private CarouselModel carouselModel;

  private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

  public List<CarouselVO> listAllCarousels() {
    try {
      DistributedTransaction transaction = carouselModel.startTransaction();
      List<CarouselVO> carouselVOList = carouselModel.listAll(transaction);
      transaction.commit();
      return carouselVOList;
    } catch (Exception e) {
      logger.error("error when list all carousels, e: " + e);
      return Lists.newArrayList();
    }
  }
}
