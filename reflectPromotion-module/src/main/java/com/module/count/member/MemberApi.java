package com.module.count.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberApi {

    @GetMapping
    public String getMembers() {
        return "getmember called";
    }

}
