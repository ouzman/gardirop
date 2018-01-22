package com.oguzhanuzman.github.gardirop.controller.rest.member;


import com.oguzhanuzman.github.gardirop.persistence.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDetailDto {
    private Long id;
    private String username;
    private String visibleName;

    public static MemberDetailDto of(Member member) {
        if (member == null) {
            return null;
        }

        return new MemberDetailDto(member.getId(), member.getUsername(), member.getScreenName());
    }
}
