package com.scalardb.demo.controller.response;

import com.google.common.collect.Lists;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.vo.OrderVO;
import com.scalardb.demo.vo.ProductVO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: ListenYoung
 * @date: Created on 14:56 2023/6/30
 * @modified By:
 */
public class GetOrderListResponse {
  public String code;
  public List<List<OrderResponse>> orders;

  public static GetOrderListResponse from(ResponseCode code, List<OrderVO> orderVOList, List<ProductVO> productVOList) {
    GetOrderListResponse response = new GetOrderListResponse();
    response.code = code.code;
    if (CollectionUtils.isEmpty(orderVOList)) {
      response.orders = Lists.newArrayList();
      return response;
    }

    Map<String, ProductVO> productVOMap = productVOList.stream().collect(Collectors.toMap(r -> r.id, r -> r, (r1, r2) -> r2));
    response.orders = orderVOList
        .stream()
        .map(orderVO -> OrderResponse.from(orderVO, productVOMap.get(orderVO.productId)))
        .collect(Collectors.groupingBy(vo -> vo.transId))
        .values()
        .stream()
        .collect(Collectors.toList());

    return response;
  }
}
