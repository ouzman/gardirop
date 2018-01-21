package com.oguzhanuzman.github.gardirop.controller.rest;

import com.oguzhanuzman.github.gardirop.Constants;
import com.oguzhanuzman.github.gardirop.configuration.SecurityPrincipal;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.MemberCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.MemberDetailDto;
import com.oguzhanuzman.github.gardirop.persistence.Member;
import com.oguzhanuzman.github.gardirop.repository.MemberRepository;
import com.oguzhanuzman.github.gardirop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/member")
public class MemberRestController {
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    @Secured(Constants.Security.Roles.ADMIN)
    public List<MemberDetailDto> listMembers() {
        return this.memberService.listDetails();
    }

    @PostMapping
    public MemberDetailDto createMember(@Valid MemberCreateDto memberCreateDto) {
        return this.memberService.create(memberCreateDto);
    }
}
