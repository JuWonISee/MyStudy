package com.firstassignment.service;

import com.firstassignment.domain.Education;
import com.firstassignment.domain.Experience;
import com.firstassignment.domain.Member;
import com.firstassignment.repository.EducationRepository;
import com.firstassignment.repository.ExperienceRepository;
import com.firstassignment.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class MyProfileService {
    private final MemberService memberService;
    private final ExperienceService experienceService;
    private final EducationService educationService;

    public MyProfileService(MemberService memberService, ExperienceService experienceService, EducationService educationService) {
        this.memberService = memberService;
        this.experienceService = experienceService;
        this.educationService = educationService;
    }

}
