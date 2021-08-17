package com.api.pipeline.blackup.controller;

import com.api.pipeline.blackup.entity.MemberEntity;
import com.api.pipeline.blackup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/blackup/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{mem_id}")
    public ResponseEntity<MemberEntity> memberInfo(@PathVariable Integer mem_id) {
        Optional<MemberEntity> member = memberService.getMember(mem_id);
        if(member.isPresent()){	//exist
            return new ResponseEntity<MemberEntity>(member.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MemberEntity>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{mem_id}")
    public ResponseEntity<MemberEntity> deleteMember(@PathVariable Integer mem_id) {
        Optional<MemberEntity> member = memberService.getMember(mem_id);
        if(member.isPresent()){	//exist
            return new ResponseEntity<MemberEntity>(member.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MemberEntity>(HttpStatus.NOT_FOUND);
        }
    }
}
