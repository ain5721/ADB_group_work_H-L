package com.scalardb.demo.controller;

import com.scalardb.demo.controller.request.CreateCollectionRequest;
import com.scalardb.demo.controller.request.DeleteCollectionRequest;
import com.scalardb.demo.controller.request.GetCollectionRequest;
import com.scalardb.demo.controller.response.CommonResponse;
import com.scalardb.demo.controller.response.GetCollectionListResponse;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.service.CollectionService;
import com.scalardb.demo.service.ProductService;
import com.scalardb.demo.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 12:56 2023/6/30
 * @modified By:
 */
@RestController
@RequestMapping("/api/user/collect")
public class CollectionController {
  @Autowired
  private CollectionService collectionService;
  @Autowired
  private ProductService productService;

  @PostMapping("/addCollect")
  public CommonResponse create(@RequestBody @Valid CreateCollectionRequest request) {
    ResponseCode responseCode = collectionService.insert(request.userId, request.productId);
    return CommonResponse.from(responseCode);
  }

  @PostMapping("/getCollect")
  public GetCollectionListResponse listCollectedProductsByUserId(@RequestBody @Valid GetCollectionRequest request) {
    List<String> productIds = collectionService
        .listByUserId(request.userId)
        .stream()
        .map(vo -> vo.productId)
        .collect(Collectors.toList());

    List<ProductVO> productVOList = productService.listByIds(productIds);

    return GetCollectionListResponse.from(ResponseCode.COMMON_SUCCESS, productVOList);
  }

  @PostMapping("/deleteCollect")
  public CommonResponse delete(@RequestBody @Valid DeleteCollectionRequest request) {
    ResponseCode responseCode = collectionService.deleteByUserIdAndProductId(request.userId, request.productId);
    return CommonResponse.from(responseCode);
  }
}
