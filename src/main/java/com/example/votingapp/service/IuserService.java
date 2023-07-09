package com.example.votingapp.service;

import com.example.votingapp.Dto.LoginDTO;
import com.example.votingapp.Dto.RegisterDTO;
import com.example.votingapp.model.Candidate;
import com.example.votingapp.model.UserModel;

import java.util.List;

public interface IuserService {
    String RegisterNewUser(RegisterDTO registerDTO);

    int login(LoginDTO loginDTO);

    String Vote(int userId, int CandidateID);

    String logoutUser(int userID);

    UserModel fetchUserData(int userID);

    List<Candidate> showAllCandiates();
}
