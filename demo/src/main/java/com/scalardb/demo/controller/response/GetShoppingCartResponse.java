package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.vo.CartVO;
import com.scalardb.demo.vo.ProductVO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 17:59 2023/6/30
 * @modified By:
 */
public class GetShoppingCartResponse {
  public String code;
  @JsonProperty("shoppingCartData")
  public List<CartResponse> cartItems;

  public static GetShoppingCartResponse from(ResponseCode code, List<CartVO> cartVOList, List<ProductVO> productVOList) {
    GetShoppingCartResponse response = new GetShoppingCartResponse();
    response.code = code.code;

    if (CollectionUtils.isEmpty(cartVOList)) {
      response.cartItems = Lists.newArrayList();
      return response;
    }

    Map<String, ProductVO> productVOMap = productVOList.stream().collect(Collectors.toMap(r -> r.id, r -> r, (r1, r2) -> r2));

    response.cartItems = cartVOList
        .stream()
        .map(item -> CartResponse.from(item, productVOMap.get(item.productId)))
        .collect(Collectors.toList());

    return response;
  }
}
