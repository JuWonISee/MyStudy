package com.firstassignment.controller;

import com.firstassignment.domain.Member;
import com.firstassignment.dto.MemberSignupRequest;
import com.firstassignment.dto.MemberUpdateRequest;
import com.firstassignment.exception.MemberException;
import com.firstassignment.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/api")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> signup(@Valid @RequestBody MemberSignupRequest request) {
        memberService.signup(request);

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/checkemail")
    @ResponseBody
    public ResponseEntity<String> checkEmail(@RequestParam("email") String email) {
        memberService.checkEmail(email);

        return ResponseEntity.ok("사용 가능한 이메일입니다.");
    }

    @GetMapping("getinfo")
    @ResponseBody
    @Transactional
    public ResponseEntity<Member> getMember(HttpSession session) {
        Member member = memberService.getMemberById((Long)session.getAttribute("memberId"))
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        return ResponseEntity.ok(member);
    }

    @PostMapping("/update")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> update(@RequestBody MemberUpdateRequest memberUpdateRequest, HttpSession session) {
        memberUpdateRequest.setId((Long)session.getAttribute("memberId"));

        memberService.updateProfile(memberUpdateRequest);

        return ResponseEntity.ok("프로필이 성공적으로 업데이트 되었습니다.");
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> delete(@RequestBody Member member, HttpSession session) {
        memberService.delete(member.getId());

        session.invalidate();

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
