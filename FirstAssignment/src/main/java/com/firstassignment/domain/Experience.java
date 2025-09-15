package com.firstassignment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "experience")
@Getter
public class Experience {
    protected Experience() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = true)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate endDate; //null이면 현재 재직 중

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;

    @Builder
    public Experience(String company, String title, LocalDate startDate, LocalDate endDate, Member member) {
        this.company = company;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
