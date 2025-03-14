package kr.co.kgc.temp.biz.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {
  
  @GetMapping("")
  public String goMain() {
    return "index";
  }
}
