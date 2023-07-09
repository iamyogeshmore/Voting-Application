package com.example.votingapp.service;

import com.example.votingapp.Dto.AddCandidateDTO;
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
public class AdminService implements IadminService {
    @Autowired
    CandidateRepo candidateRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    VotingDataRepo votingDataRepo;

    //--------------------------------- Register Admin ---------------------------------
    @Override
    public String RegisterAdmin(RegisterDTO registerDTO) {
        if (userRepo.findByEmailId(registerDTO.getEmailId()) == null) {
            UserModel registerUser = new UserModel(registerDTO.getUserName(), registerDTO.getEmailId(), registerDTO.getPhoneNo(), registerDTO.getPassword());
            registerUser.setRole("admin");
            userRepo.save(registerUser);
            return "Admin Register Successful";
        }
        throw new VotingAppException("Admin Already Exist");
    }

    //--------------------------------- Admin Login ---------------------------------
    @Override
    public int adminLogin(LoginDTO loginDTO) {
        UserModel userModel = userRepo.findByEmailId(loginDTO.getEmailId());
        if (userModel != null && userModel.getRole().equals("admin")) {
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

    //--------------------------------- Admin Logout ---------------------------------
    @Override
    public String logoutAdmin(int adminId) {
        UserModel admin = userRepo.findById(adminId).get();
        if (admin.isLogin() && admin.getRole().equals("admin")) {
            admin.setLogin(false);
            userRepo.save(admin);
            return "Logout Successfully";
        }
        throw new VotingAppException("Already Logout");
    }

    //--------------------------------- Add Candidate ---------------------------------
    @Override
    public String AddCandidate(AddCandidateDTO addCandidateDTO) {
        Candidate candidate = new Candidate(addCandidateDTO.getCandidateName());
        candidateRepo.save(candidate);
        return "Candidate Added successfully";
    }

    //--------------------------------- All Candidate Votes ---------------------------------
    @Override
    public List<Candidate> showAllCandiates(int adminId) {
        UserModel admin = userRepo.findById(adminId).get();
        if (admin.isLogin() && admin.getRole().equals("admin")) {
            return candidateRepo.findAll();
        }
        throw new VotingAppException("Please Login as Admin");
    }

    //--------------------------------- Candidate Votes ---------------------------------
    @Override
    public List<VotingData> getVotingRecord(int candidateId) {
        List<VotingData> votingData = votingDataRepo.findAllByCandidateId(candidateId);
        Candidate candidate = candidateRepo.findById(candidateId).get();
        candidate.setVotingCount(votingData.size());
        candidateRepo.save(candidate);
        return votingData;
    }
}
