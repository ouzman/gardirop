package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.MemberCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.MemberDetailDto;
import com.oguzhanuzman.github.gardirop.exception.MemberAlreadyExists;
import com.oguzhanuzman.github.gardirop.persistence.Member;
import com.oguzhanuzman.github.gardirop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDetailDto> listDetails() {
        return this.memberRepository.findAll().stream().map(MemberDetailDto::of).collect(Collectors.toList());
    }

    public MemberDetailDto create(MemberCreateDto memberCreateDto) {
        Member member = this.memberRepository.findByUsername(memberCreateDto.getUsername());
        if (member != null) {
            throw new MemberAlreadyExists();
        }

        Member newMember = new Member(memberCreateDto.getScreenName(), memberCreateDto.getUsername(), memberCreateDto.getPassword());
        newMember = this.memberRepository.save(newMember);

        return MemberDetailDto.of(newMember);
    }
}
