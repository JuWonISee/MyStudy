package com.firstassignment.common.handler;

import com.firstassignment.domain.Member;
import com.firstassignment.exception.MemberException;
import com.firstassignment.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberRepository memberRepository;

    public CustomLoginSuccessHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberException("존재하지 않는 아이디입니다."));

        HttpSession session = request.getSession();
        session.setAttribute("memberId", member.getId());
        session.setAttribute("memberName", member.getName());

        response.sendRedirect("/");

    }
}
