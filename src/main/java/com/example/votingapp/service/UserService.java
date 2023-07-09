package com.example.votingapp.service;

import com.example.votingapp.Dto.LoginDTO;
import com.example.votingapp.Dto.RegisterDTO;
import com.example.votingapp.exception.VotingAppException;
import com.example.votingapp.model.Candidate;
import com.example.votingapp.model.UserModel;
import com.example.votingapp.model.VotingData;
import com.example.votingapp.repository.CandidateRepo;
import com.example.votingapp.repository.UserRepo;
import com.example.votingapp.repository.VotingDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IuserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    VotingDataRepo votingDataRepo;
    @Autowired
    CandidateRepo candidateRepo;

    //--------------------------------- Register New User ---------------------------------
    @Override
    public String RegisterNewUser(RegisterDTO registerDTO) {
        if (userRepo.findByEmailId(registerDTO.getEmailId()) == null) {
            UserModel registerUser = new UserModel(registerDTO.getUserName(), registerDTO.getEmailId(), registerDTO.getPhoneNo(), registerDTO.getPassword());
            userRepo.save(registerUser);
            return "User Register Successful";
        }
        throw new VotingAppException("There is already an account registered with the same email");
    }

    //--------------------------------- User Login ---------------------------------
    @Override
    public int login(LoginDTO loginDTO) {
        UserModel userModel = userRepo.findByEmailId(loginDTO.getEmailId());
        if (userModel != null) {
            if (userModel.getPassword().equals(loginDTO.getPassword())) {
                userModel.setLogin(true);
                userModel.setId(userModel.getId());
                userRepo.save(userModel);
                return userModel.getId();
            }
            throw new VotingAppException("please check Your Password");
        }
        throw new VotingAppException("Check Your Email-ID");
    }

    //--------------------------------- User Vote For Candidate ---------------------------------
    @Override
    public String Vote(int userId, int candidateId) {
        UserModel user = userRepo.findById(userId).get();
        Candidate candidate = candidateRepo.findById(candidateId).get();
        if (user.isLogin()) {
            if (votingDataRepo.findByUserId(user.getId()) == null) {
                VotingData votingData = new VotingData(user.getId(), candidateId);
                candidate.setVotingCount(candidate.getVotingCount() + 1);
                user.setVoted(true);
                userRepo.save(user);
                candidateRepo.save(candidate);
                votingDataRepo.save(votingData);
                return "Vote Added successfully";
            }
            throw new VotingAppException("Voting is already Completed");
        }
        throw new VotingAppException("Invalid User");
    }

    //---------------------------------  User Logout ---------------------------------
    @Override
    public String logoutUser(int userID) {
        UserModel userModel = userRepo.findById(userID).get();
        if (userModel.isLogin()) {
            userModel.setLogin(false);
            userRepo.save(userModel);
            return "Logout Successfully";
        }
        throw new VotingAppException("Already LogOut");
    }

    //---------------------------------  User Data ---------------------------------
    @Override
    public UserModel fetchUserData(int userID) {
        UserModel userModel = userRepo.findById(userID).get();
        if (userModel.isLogin()) {
            return userModel;
        }
        throw new VotingAppException("please login first");
    }

    //---------------------------------  Show All Candidate for voting ---------------------------------
    @Override
    public List<Candidate> showAllCandiates() {
        return candidateRepo.findAll();
    }
}
