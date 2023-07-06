package com.scalardb.demo.controller.response;

import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.vo.UserVO;

/**
 * @author: ListenYoung
 * @date: Created on 19:53 2023/6/30
 * @modified By:
 */
public class LoginResponse {
  public String code;
  public String msg;
  public UserResponse user;

  public static LoginResponse from(ResponseCode responseCode, UserVO userVO) {
    LoginResponse response = new LoginResponse();
    response.code = responseCode.code;
    response.msg = responseCode.desc;
    response.user = UserResponse.from(userVO);

    return response;
  }
}
