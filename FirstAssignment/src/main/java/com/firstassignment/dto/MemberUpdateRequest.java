package com.firstassignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateRequest {
    private Long id;
    private String status;
    private String company;
    private String domain;
    private String city;
    private String skills;
    private String github;
    private String yourself;
    private String twitter;
    private String facebook;
    private String youtube;
    private String linkedin;
    private String instagram;
}
