package com.example.votingapp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class VotingData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private int userId;
    private int candidateId;

    public VotingData(int userId, int candidateId) {
        this.userId = userId;
        this.candidateId = candidateId;
    }
}
