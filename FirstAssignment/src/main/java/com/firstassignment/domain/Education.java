package com.firstassignment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "education")
@Getter
public class Education {
    protected Education() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String school;

    @Column(nullable = true)
    private String degree;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate; //null이면 현재 재직 중

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;

    @Builder
    public Education(String school, String degree, LocalDate startDate, LocalDate endDate, Member member) {
        this.school = school;
        this.degree = degree;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
