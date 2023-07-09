package com.example.votingapp.service;

import com.example.votingapp.Dto.AddCandidateDTO;
import com.example.votingapp.Dto.LoginDTO;
import com.example.votingapp.Dto.RegisterDTO;
import com.example.votingapp.model.Candidate;
import com.example.votingapp.model.VotingData;

import java.util.List;

public interface IadminService {
    String AddCandidate(AddCandidateDTO addCandidateDTO);

    List<Candidate> showAllCandiates(int adminID);

    List<VotingData> getVotingRecord(int candidateId);

    String RegisterAdmin(RegisterDTO registerDTO);

    int adminLogin(LoginDTO loginDTO);

    String logoutAdmin(int adminId);

}
