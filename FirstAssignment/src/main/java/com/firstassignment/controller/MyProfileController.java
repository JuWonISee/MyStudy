package com.firstassignment.controller;

import com.firstassignment.domain.Education;
import com.firstassignment.domain.Experience;
import com.firstassignment.domain.Member;
import com.firstassignment.service.EducationService;
import com.firstassignment.service.ExperienceService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/myprofile")
public class MyProfileController {
    private final ExperienceService experienceService;
    private final EducationService educationService;

    public MyProfileController(ExperienceService experienceService, EducationService educationService) {
        this.experienceService = experienceService;
        this.educationService = educationService;
    }

    @GetMapping
    public String myProfile(Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");

        List<Experience> experienceList = experienceService.getExperience(memberId);
        List<Education> educationList = educationService.getEducation(memberId);

        model.addAttribute("memberId", memberId);
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("experienceList", experienceList);
        model.addAttribute("educationList", educationList);

        return "profile/myprofile";
    }

    @DeleteMapping("/exp/delete")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> deleteExperience(@RequestBody Experience experience) {
        experienceService.delete(experience.getId());

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

    @DeleteMapping("/edu/delete")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> deleteEducation(@RequestBody Education education) {
        educationService.delete(education.getId());

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

    @PostMapping("/exp/add")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> addExperience(@RequestBody Experience experience, HttpSession session) {
        Member member = Member.builder().
                id((Long)session.getAttribute("memberId")).
                build();

        experience.setMember(member);

        experienceService.addExperience(experience);

        return ResponseEntity.ok("경력이 추가되었습니다.");
    }

    @PostMapping("/edu/add")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> addEducation(@RequestBody Education education, HttpSession session) {
        Member member = Member.builder().
                id((Long)session.getAttribute("memberId")).
                build();

        education.setMember(member);

        educationService.addEducation(education);

        return ResponseEntity.ok("학력이 추가되었습니다.");
    }
}
