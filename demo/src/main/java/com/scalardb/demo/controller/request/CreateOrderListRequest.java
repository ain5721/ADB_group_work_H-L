package com.scalardb.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author: ListenYoung
 * @date: Created on 15:19 2023/6/30
 * @modified By:
 */
public class CreateOrderListRequest {
  @NotBlank
  @JsonProperty("user_id")
  public String userId;
  @JsonProperty("products")
  public List<CreateOrderRequest> orders;
}
