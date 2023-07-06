package com.scalardb.demo.enums;

/**
 * @author: ListenYoung
 * @date: Created on 12:46 2023/6/30
 * @modified By:
 */
public enum ResponseCode {
  COMMON_SUCCESS("001", "Success"),
  UPDATE_CART_SUCCESS("002", "Updated quantity success"),
  USER_NAME_EXIST("996", "User name already exists"),
  LOGIN_FAILED("997", "Incorrect username or password"),
  OVER_LIMIT("998", "Exceed the limit"),
  COMMON_FAILED("999", "Failed"),
      ;
  public String code;
  public String desc;

  ResponseCode(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static ResponseCode fromCode(String code) {
    for (ResponseCode responseCode : ResponseCode.values()) {
      if (responseCode.code.equals(code)) {
        return responseCode;
      }
    }
    throw new RuntimeException("Error when get response code from code, code = " + code);
  }
}
