package com.scalardb.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author: ListenYoung
 * @date: Created on 15:46 2023/6/30
 * @modified By:
 */
public class SearchProductRequest {
  @NotBlank
  @JsonProperty("search")
  public String keyword;
  @NotNull
  public Integer currentPage;
  @NotNull
  public Integer pageSize;
}
