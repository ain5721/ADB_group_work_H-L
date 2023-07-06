package com.scalardb.demo.controller.request;

import javax.validation.constraints.NotBlank;

/**
 * @author: ListenYoung
 * @date: Created on 16:08 2023/6/30
 * @modified By:
 */
public class GetPromoProductRequest {
  @NotBlank
  public String categoryName;
}
