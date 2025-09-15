package com.firstassignment.service;

import com.firstassignment.domain.Member;
import com.firstassignment.exception.MemberException;
import com.firstassignment.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public  CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException("존재하지 않는 아이디입니다."));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles("USER")
                .build();
    }
}
