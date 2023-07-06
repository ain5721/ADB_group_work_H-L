package com.scalardb.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author: ListenYoung
 * @date: Created on 18:05 2023/6/30
 * @modified By:
 */
public class GetShoppingCartRequest {
  @NotBlank
  @JsonProperty("user_id")
  public String userId;
}
