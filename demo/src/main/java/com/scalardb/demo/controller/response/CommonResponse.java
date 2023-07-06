package com.scalardb.demo.controller.response;

import com.scalardb.demo.enums.ResponseCode;

/**
 * @author: ListenYoung
 * @date: Created on 13:01 2023/6/30
 * @modified By:
 */
public class CommonResponse {
  public String code;
  public String msg;

  public static CommonResponse from(String code, String msg) {
    CommonResponse response = new CommonResponse();
    response.code = code;
    response.msg = msg;

    return response;
  }

  public static CommonResponse from(ResponseCode code) {
    return from(code.code, code.desc);
  }
}
