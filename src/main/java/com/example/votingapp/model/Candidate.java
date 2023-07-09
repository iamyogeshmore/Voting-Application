package com.example.votingapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    private String candidateName;
    private int votingCount;

    public Candidate(String candidateName) {
        this.candidateName = candidateName;
    }
}
