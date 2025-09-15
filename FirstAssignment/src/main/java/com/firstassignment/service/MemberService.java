package com.firstassignment.service;

import com.firstassignment.domain.Member;
import com.firstassignment.dto.MemberSignupRequest;
import com.firstassignment.dto.MemberUpdateRequest;
import com.firstassignment.exception.MemberException;
import com.firstassignment.repository.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(MemberSignupRequest request) {
        checkEmail(request.getEmail());

        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        memberRepository.save(member);
    }

    public void checkEmail(String email) {
        if (!email.matches("^[a-zA-Z][a-zA-Z0-9]*@[a-zA-Z]+\\.(com|net|org)$")) {
            throw new MemberException("이메일 형식이 올바르지 않습니다. ex)a123@naver.com");
        }

        if(memberRepository.existsByEmail(email)) {
            throw new MemberException("이미 사용 중인 이메일입니다.");
        }
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public void updateProfile(MemberUpdateRequest request) {
        Member member = memberRepository.findById(request.getId())
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        member.updateProfile(request);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
