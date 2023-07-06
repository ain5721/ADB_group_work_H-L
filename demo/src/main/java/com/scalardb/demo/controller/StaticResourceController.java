package com.scalardb.demo.controller;

import com.scalardb.demo.controller.response.CarouselListResponse;
import com.scalardb.demo.enums.ResponseCode;
import com.scalardb.demo.service.StaticResourceService;
import com.scalardb.demo.vo.CarouselVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

/**
 * @author: ListenYoung
 * @date: Created on 17:33 2023/6/30
 * @modified By:
 */
@RestController
@RequestMapping("/api/resources")
public class StaticResourceController {
  @Autowired
  private StaticResourceService staticResourceService;

  @PostMapping("/carousel")
  public CarouselListResponse listAllCarousels() {
    List<CarouselVO> carouselVOList = staticResourceService.listAllCarousels();

    return CarouselListResponse.from(ResponseCode.COMMON_SUCCESS, carouselVOList);
  }

  @GetMapping(value = "/readme", produces = MediaType.TEXT_MARKDOWN_VALUE)
  public byte[] fileDownLoad() throws IOException {
    File file = new File("/Users/ListenYoung/Documents/java/demo/README.md");
    FileInputStream inputStream = new FileInputStream(file);
    byte[] bytes = new byte[inputStream.available()];
    inputStream.read(bytes, 0, inputStream.available());
    return bytes;
  }
}
