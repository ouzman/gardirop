package com.oguzhanuzman.github.gardirop.controller.rest.dto;


import com.oguzhanuzman.github.gardirop.persistence.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDetailDto {
    private Long id;
    private String username;
    private String visibleName;

    public static MemberDetailDto of(Member member) {
        return new MemberDetailDto(member.getId(), member.getUsername(), member.getScreenName());
    }
}
