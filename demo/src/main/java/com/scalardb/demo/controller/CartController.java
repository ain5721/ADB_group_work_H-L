package com.scalardb.demo.controller;

import com.google.common.collect.Lists;
import com.scalardb.demo.controller.request.CreateCartRequest;
import com.scalardb.demo.controller.request.DeleteCartRequest;
import com.scalardb.demo.controller.request.GetShoppingCartRequest;
import com.scalardb.demo.controller.request.UpdateCartRequest;
import com.scalardb.demo.controller.response.CommonResponse;
import com.scalardb.demo.controller.response.CreateShoppingCartResponse;
import com.scalardb.demo.controller.response.GetShoppingCartResponse;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.service.CartService;
import com.scalardb.demo.service.ProductService;
import com.scalardb.demo.vo.CartVO;
import com.scalardb.demo.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 17:51 2023/6/30
 * @modified By:
 */
@RestController
@RequestMapping("/api/user/shoppingCart")
public class CartController {
  @Autowired
  private CartService cartService;
  @Autowired
  private ProductService productService;

  @PostMapping("/getShoppingCart")
  public GetShoppingCartResponse listCartByUserId(@RequestBody @Valid GetShoppingCartRequest request) {
    List<CartVO> cartVOList = cartService.listByUserId(request.userId);
    if (CollectionUtils.isEmpty(cartVOList)) {
      return GetShoppingCartResponse.from(ResponseCode.COMMON_SUCCESS, cartVOList, Lists.newArrayList());
    }

    List<ProductVO> productVOList = productService.listByIds(cartVOList.stream().map(vo -> vo.productId).collect(Collectors.toList()));

    return GetShoppingCartResponse.from(ResponseCode.COMMON_SUCCESS, cartVOList, productVOList);
  }

  @PostMapping("/addShoppingCart")
  public CreateShoppingCartResponse insertOrUpdate(@RequestBody @Valid CreateCartRequest request) {
    ResponseCode responseCode = cartService.insertOrUpdate(request.userId, request.productId, 1);
    if (responseCode != ResponseCode.COMMON_SUCCESS && responseCode != ResponseCode.UPDATE_CART_SUCCESS) {
      return CreateShoppingCartResponse.from(responseCode, Lists.newArrayList(), Lists.newArrayList());
    }

    List<CartVO> cartVOList = cartService.listByUserId(request.userId);
    if (CollectionUtils.isEmpty(cartVOList)) {
      return CreateShoppingCartResponse.from(ResponseCode.COMMON_FAILED, cartVOList, Lists.newArrayList());
    }

    List<ProductVO> productVOList = productService.listByIds(cartVOList.stream().map(vo -> vo.productId).collect(Collectors.toList()));

    return CreateShoppingCartResponse.from(responseCode, cartVOList, productVOList);
  }

  @PostMapping("/deleteShoppingCart")
  public CommonResponse delete(@RequestBody @Valid DeleteCartRequest request) {
    return CommonResponse.from(cartService.delete(request.userId, request.productId));
  }

  @PostMapping("/updateShoppingCart")
  public CommonResponse update(@RequestBody @Valid UpdateCartRequest request) {
    return CommonResponse.from(cartService.update(request.userId, request.productId, request.count));
  }
}
