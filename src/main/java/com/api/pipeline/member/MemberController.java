package com.api.pipeline.member;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @RequestMapping("/all")
    public MemberEntity members () {

    }
}
