package com.scalardb.demo.controller;

import com.scalardb.demo.controller.request.CheckUserNameRequest;
import com.scalardb.demo.controller.request.LoginRequest;
import com.scalardb.demo.controller.request.RegisterRequest;
import com.scalardb.demo.controller.response.CommonResponse;
import com.scalardb.demo.controller.response.LoginResponse;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.service.UserService;
import com.scalardb.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: ListenYoung
 * @date: Created on 19:50 2023/6/30
 * @modified By:
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody @Valid LoginRequest request) {
    UserVO userVO = userService.findByNameAndPassword(request.name, request.password);
    if (userVO == null) {
      return LoginResponse.from(ResponseCode.LOGIN_FAILED, new UserVO());
    } else {
      return LoginResponse.from(ResponseCode.COMMON_SUCCESS, userVO);
    }
  }

  @PostMapping("/findUserName")
  public CommonResponse login(@RequestBody @Valid CheckUserNameRequest request) {
    return CommonResponse.from(userService.checkUserNameExistOrNot(request.name));
  }

  @PostMapping("/register")
  public CommonResponse register(@RequestBody @Valid RegisterRequest request) {
    return CommonResponse.from(userService.insert(request.name, request.password));
  }
}
