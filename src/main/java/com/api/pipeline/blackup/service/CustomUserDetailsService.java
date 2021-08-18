package com.api.pipeline.blackup.service;

import com.api.pipeline.blackup.entity.MemberEntity;
import com.api.pipeline.blackup.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String id) {
        return memberRepository.findById(id)
                .map(member -> createUser(id, member))
                .orElseThrow(() -> new UsernameNotFoundException(id + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(String id, MemberEntity member) {
        if (member.getId().isEmpty()) {
            throw new RuntimeException(id + " -> 활성화되어 있지 않습니다.");
        }
        List<Boolean> list = Arrays.asList(true);
        HashSet<String> authorities = new HashSet<String>();
        authorities.add("ROLE_USER");
        List<GrantedAuthority> grantedAuthorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        System.out.println("grantedAuthorities ==>" + grantedAuthorities);

        return new org.springframework.security.core.userdetails.User(member.getId(),
                member.getPassword(),
                grantedAuthorities);
    }
}