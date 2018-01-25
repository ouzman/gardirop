package com.oguzhanuzman.github.gardirop.controller.rest;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.member.MemberCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.member.MemberDetailDto;
import com.oguzhanuzman.github.gardirop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.oguzhanuzman.github.gardirop.Constants.Security.Role;

@RestController
@RequestMapping("/api/member")
public class MemberRestController {
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    @Secured(Role.ADMIN)
    public List<MemberDetailDto> listMembers() {
        return this.memberService.listDetails();
    }

    @PostMapping
    public MemberDetailDto createMember(@Valid MemberCreateDto memberCreateDto) {
        return this.memberService.create(memberCreateDto);
    }
}
