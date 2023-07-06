package com.scalardb.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scalardb.demo.vo.UserVO;

/**
 * @author: ListenYoung
 * @date: Created on 19:51 2023/6/30
 * @modified By:
 */
public class UserResponse {
  @JsonProperty("user_id")
  public String id;
  @JsonProperty("userName")
  public String name;

  public static UserResponse from(UserVO userVO) {
    UserResponse response = new UserResponse();
    response.id = userVO.id;
    response.name = userVO.name;

    return response;
  }
}
