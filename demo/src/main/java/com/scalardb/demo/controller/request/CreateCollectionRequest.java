package com.scalardb.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author: ListenYoung
 * @date: Created on 12:57 2023/6/30
 * @modified By:
 */
public class CreateCollectionRequest {
  @NotBlank
  @JsonProperty("user_id")
  public String userId;
  @NotBlank
  @JsonProperty("product_id")
  public String productId;
}
