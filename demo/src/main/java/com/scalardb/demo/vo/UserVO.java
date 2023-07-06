package com.scalardb.demo.vo;

/**
 * @author: ListenYoung
 * @date: Created on 20:03 2023/6/22
 * @modified By:
 */
public class UserVO {
  public String id;
  public String name;
  public String password;

  public static UserVO from(String id, String name, String password) {
    UserVO vo = new UserVO();
    vo.id = id;
    vo.name = name;
    vo.password = password;

    return vo;
  }
}
