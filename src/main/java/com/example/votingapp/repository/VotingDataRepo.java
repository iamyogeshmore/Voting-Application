package com.example.votingapp.repository;

import com.example.votingapp.model.VotingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingDataRepo extends JpaRepository<VotingData, Integer> {
    List<VotingData> findAllByCandidateId(int candidateID);

    VotingData findByUserId(int userId);
}
