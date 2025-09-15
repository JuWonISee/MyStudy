package com.firstassignment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "member")
@Getter
public class Member {

    protected Member() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String status;

    @Column(nullable = true)
    private String company;

    @Column(nullable = true)
    private String domain;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String skills;

    @Column(nullable = true)
    private String github;

    @Column(nullable = true)
    private String yourself;

    @Column(nullable = true)
    private String twitter;

    @Column(nullable = true)
    private String facebook;

    @Column(nullable = true)
    private String youtube;

    @Column(nullable = true)
    private String linkedin;

    @Column(nullable = true)
    private String instagram;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    @Builder
    public Member(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void updateProfile(com.firstassignment.dto.MemberUpdateRequest request) {
        this.status = request.getStatus();
        this.company = request.getCompany();
        this.domain = request.getDomain();
        this.city = request.getCity();
        this.skills = request.getSkills();
        this.github = request.getGithub();
        this.yourself = request.getYourself();
        this.twitter = request.getTwitter();
        this.facebook = request.getFacebook();
        this.youtube = request.getYoutube();
        this.linkedin = request.getLinkedin();
        this.instagram = request.getInstagram();
    }
}