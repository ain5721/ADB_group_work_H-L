package com.scalardb.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: ListenYoung
 * @date: Created on 15:46 2023/6/30
 * @modified By:
 */
public class GetProductByCategoryRequest {
  @NotEmpty
  @JsonProperty("categoryID")
  public List<Integer> categoryIds;
  @NotNull
  public Integer currentPage;
  @NotNull
  public Integer pageSize;
}
