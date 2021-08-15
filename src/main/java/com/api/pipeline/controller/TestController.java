package com.api.pipeline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/helloworld")
public class TestController {

    @GetMapping(value = "/string")
    @ResponseBody
    public String helloWordString () {
        return "Hello world";
    }

//    public Hello helloWorldJson () {
//
//    }
    @GetMapping(value = "/page")
    public String helloWordPage () {
        return "index.html";
    }

}
