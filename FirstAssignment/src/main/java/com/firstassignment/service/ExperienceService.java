package com.firstassignment.service;

import com.firstassignment.domain.Experience;
import com.firstassignment.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public List<Experience> getExperience(Long id) {
        return experienceRepository.findAllByMemberId(id);
    }

    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }

    public void addExperience(Experience experience) {
        experienceRepository.save(experience);
    }
}
