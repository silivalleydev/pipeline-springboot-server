package com.api.pipeline.main.service;

import com.api.pipeline.erp.entity.User;
import com.api.pipeline.erp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userId) {
        System.out.println("????????????rrr====> " + userRepository.findAllByUserId(userId));
        return userRepository.findAllByUserId(userId)
                .map(user -> createUser(userId, user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String userId, User user) {
        if (user.getUserId().isEmpty()) {
            throw new RuntimeException(userId + " -> 활성화되어 있지 않습니다.");
        }


        GrantedAuthority userGrantedAuthority =  new SimpleGrantedAuthority(user.getAuthority().getAuthorCode());

        System.out.println("userGrantedAuthority");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(userGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getUserId(),
                user.getPassword(),
                grantedAuthorities);
    }
}