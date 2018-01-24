package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.member.MemberCreateDto;
import com.oguzhanuzman.github.gardirop.enums.Permission;
import com.oguzhanuzman.github.gardirop.exception.MemberAlreadyExists;
import com.oguzhanuzman.github.gardirop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemberService.class)
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    public void generateAdminPermissionForFirstMember() throws Exception {
        when(memberRepository.count()).thenReturn(0L);
        List<Permission> generatedPermissions = memberService.generatePermissionsToCreate();
        assertThat(generatedPermissions).contains(Permission.ADMIN);
    }

    @Test
    public void doNotGenerateAdminPermissionForNonFirstMember() throws Exception {
        when(memberRepository.count()).thenReturn(1L);
        List<Permission> generatedPermissions = memberService.generatePermissionsToCreate();
        assertThat(generatedPermissions).doesNotContain(Permission.ADMIN);
    }


    @Test(expected = MemberAlreadyExists.class)
    public void throwExceptionOnMemberCreateIfProvidedUsernameAlreadyInUse() throws Exception {
        when(memberRepository.existsByUsername(anyString())).thenReturn(true);
        memberService.create(new MemberCreateDto());
    }

    @Test
    public void returnNullIfUsernameIsBlank() throws Exception {
        assertThat(memberService.findByUsername("")).isEqualTo(null);
    }

    @Test
    public void returnNullIfUsernameIsNull() throws Exception {
        assertThat(memberService.findByUsername(null)).isEqualTo(null);
    }

}