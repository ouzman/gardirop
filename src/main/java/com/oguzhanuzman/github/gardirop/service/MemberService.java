package com.oguzhanuzman.github.gardirop.service;

import com.google.common.annotations.VisibleForTesting;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.member.MemberCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.member.MemberDetailDto;
import com.oguzhanuzman.github.gardirop.enums.Permission;
import com.oguzhanuzman.github.gardirop.exception.member.MemberAlreadyExists;
import com.oguzhanuzman.github.gardirop.persistence.Member;
import com.oguzhanuzman.github.gardirop.repository.MemberRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
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
        return this.memberRepository.findAll().stream()
                .map(MemberDetailDto::of)
                .collect(Collectors.toList());
    }

    public MemberDetailDto create(MemberCreateDto memberCreateDto) {
        validateCreateRequirements(memberCreateDto);

        Member newMember = this.memberRepository.save(
                new Member(
                        memberCreateDto.getScreenName(),
                        memberCreateDto.getUsername(),
                        memberCreateDto.getPassword(),
                        this.generatePermissionsToCreate()
                )
        );

        return MemberDetailDto.of(newMember);
    }

    @VisibleForTesting
    List<Permission> generatePermissionsToCreate() {
        boolean newMemberIsFirstMember = memberRepository.count() == 0;

        if (newMemberIsFirstMember) {
            return Arrays.asList(Permission.MEMBER, Permission.ADMIN);
        } else {
            return Collections.singletonList(Permission.MEMBER);
        }
    }

    public Member findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return this.memberRepository.findByUsername(username);
    }

    private void validateCreateRequirements(MemberCreateDto memberCreateDto) {
        if (this.memberRepository.existsByUsername(memberCreateDto.getUsername())) {
            throw new MemberAlreadyExists();
        }
    }
}
