package com.scalardb.demo.controller;

import com.google.common.collect.Lists;
import com.scalardb.demo.controller.request.*;
import com.scalardb.demo.controller.response.*;
import com.scalardb.demo.enums.ProductCategory;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.service.ProductImageService;
import com.scalardb.demo.service.ProductService;
import com.scalardb.demo.vo.ProductImageVO;
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
 * @date: Created on 15:40 2023/6/30
 * @modified By:
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductImageService productImageService;

  private static final Integer PROMO_AND_HOT_PRODUCT_PAGE_SIZE = 7;

  @PostMapping("/getAllProduct")
  public GetAllProductResponse listAll(@RequestBody @Valid GetAllProductRequest request) {
    List<ProductVO> productVOList = productService.listAll();
    int total = productVOList.size();
    int skip = (request.currentPage - 1) * request.pageSize;
    productVOList = productVOList.stream().skip(skip).limit(request.pageSize).collect(Collectors.toList());

    return GetAllProductResponse.from(ResponseCode.COMMON_SUCCESS, total, productVOList);
  }

  @PostMapping("/getPromoProduct")
  public GetPromoProductResponse listPromoProducts(@RequestBody @Valid GetPromoProductRequest request) {
    List<ProductCategory> categories = Lists.newArrayList(ProductCategory.fromDesc(request.categoryName));
    List<ProductVO> productVOList = productService.listByCategories(categories);
    productVOList = productVOList.stream().limit(PROMO_AND_HOT_PRODUCT_PAGE_SIZE).collect(Collectors.toList());

    return GetPromoProductResponse.from(ResponseCode.COMMON_SUCCESS, productVOList);
  }

  @PostMapping("/getHotProduct")
  public GetHotProductResponse listHotProducts(@RequestBody @Valid GetHotProductRequest request) {
    List<ProductCategory> categories = request.categoryNames.stream().map(ProductCategory::fromDesc).collect(Collectors.toList());
    List<ProductVO> productVOList = productService.listByCategories(categories);
    productVOList = productVOList.stream().limit(PROMO_AND_HOT_PRODUCT_PAGE_SIZE).collect(Collectors.toList());

    return GetHotProductResponse.from(ResponseCode.COMMON_SUCCESS, productVOList);
  }

  @PostMapping("/getProductByCategory")
  public GetProductByCategoryResponse listProductsByCategories(@RequestBody @Valid GetProductByCategoryRequest request) {
    List<ProductCategory> categories = request.categoryIds.stream().map(ProductCategory::fromCode).collect(Collectors.toList());
    List<ProductVO> productVOList = productService.listByCategories(categories);
    int total = productVOList.size();
    int skip = (request.currentPage - 1) * request.pageSize;
    productVOList = productVOList.stream().skip(skip).limit(request.pageSize).collect(Collectors.toList());

    return GetProductByCategoryResponse.from(ResponseCode.COMMON_SUCCESS, total, productVOList);
  }

  @PostMapping("/getCategory")
  public GetCategoryResponse listCategories() {
    return GetCategoryResponse.from(ResponseCode.COMMON_SUCCESS, productService.getCategoriesInfo());
  }

  @PostMapping("/getDetails")
  public GetDetailsResponse getDetails(@RequestBody @Valid GetDetailsRequest request) {
    ProductVO productVO = productService.findById(request.id);

    List<ProductVO> productVOList = productVO == null ? Lists.newArrayList() : Lists.newArrayList(productVO);
    return GetDetailsResponse.from(ResponseCode.COMMON_SUCCESS, productVOList);
  }

  @PostMapping("/getDetailsPicture")
  public GetDetailsPictureResponse getDetailsPicture(@RequestBody @Valid GetDetailsPictureRequest request) {
    List<ProductImageVO> imageVOList = productImageService.listImagesByProductId(request.id);

    return GetDetailsPictureResponse.from(ResponseCode.COMMON_SUCCESS, imageVOList);
  }

  @PostMapping("/getProductBySearch")
  public SearchProductResponse searchProduct(@RequestBody @Valid SearchProductRequest request) {
    List<ProductVO> productVOList = productService.search(request.keyword);
    int total = productVOList.size();
    int skip = (request.currentPage - 1) * request.pageSize;
    productVOList = productVOList.stream().skip(skip).limit(request.pageSize).collect(Collectors.toList());

    return SearchProductResponse.from(ResponseCode.COMMON_SUCCESS, total, productVOList);
  }
}