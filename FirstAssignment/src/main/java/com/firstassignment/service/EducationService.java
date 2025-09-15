package com.firstassignment.service;

import com.firstassignment.domain.Education;
import com.firstassignment.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getEducation(Long id) {
        return educationRepository.findAllByMemberId(id);
    }

    public void delete(Long id) {
        educationRepository.deleteById(id);
    }

    public void addEducation(Education education) {
        educationRepository.save(education);
    }
}
