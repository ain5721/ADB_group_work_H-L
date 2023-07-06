package com.scalardb.demo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author: ListenYoung
 * @date: Created on 20:07 2023/6/30
 * @modified By:
 */
public class CheckUserNameRequest {
  @NotBlank
  @JsonProperty("userName")
  public String name;
}
