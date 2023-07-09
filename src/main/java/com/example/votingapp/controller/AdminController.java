package com.example.votingapp.controller;

import com.example.votingapp.Dto.AddCandidateDTO;
import com.example.votingapp.Dto.LoginDTO;
import com.example.votingapp.Dto.RegisterDTO;
import com.example.votingapp.Response;
import com.example.votingapp.model.Candidate;
import com.example.votingapp.model.VotingData;
import com.example.votingapp.service.IadminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AdminPage")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AdminController {

    @Autowired
    IadminService iadminService;

    //--------------------------------- Register Admin ---------------------------------
    @PostMapping("/Register_Admin")
    public ResponseEntity<Response> registerAdmin(@RequestBody RegisterDTO registerDTO) {
        iadminService.RegisterAdmin(registerDTO);
        Response response = new Response("Admin Added", "Admin Registered Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Admin Login ---------------------------------
    @PostMapping("/Login")
    public ResponseEntity<Response> loginAdmin(@RequestBody LoginDTO loginDTO) {
        int adminId = iadminService.adminLogin(loginDTO);
        Response response = new Response(adminId, "Login Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Admin Login ---------------------------------
    @PostMapping("/Logout")
    public ResponseEntity<Response> logoutAdmin(@RequestParam int adminID) {
        iadminService.logoutAdmin(adminID);
        Response response = new Response("Logout", "Logout Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Add Candidate ---------------------------------
    @PostMapping("/Add_Candidate")
    public ResponseEntity<Response> addCandidate(@RequestBody AddCandidateDTO addCandidateDTO) {
        iadminService.AddCandidate(addCandidateDTO);
        Response response = new Response(addCandidateDTO, "Candidate Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Candidate Votes ---------------------------------
    @GetMapping("/CandidateVotes")
    public ResponseEntity<Response> getCandidateVotingRecord(@RequestParam int candidateID) {
        List<VotingData> votingDataList = iadminService.getVotingRecord(candidateID);
        Response response = new Response(votingDataList, "candidate Have " + votingDataList.size() + " Votes");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- All Candidate Votes ---------------------------------
    @GetMapping("/Show_All_Candidates")
    public ResponseEntity<Response> getCandidateData(@RequestParam int adminId) {
        List<Candidate> candidateList = iadminService.showAllCandiates(adminId);
        Response response = new Response(candidateList, "We have " + candidateList.size() + " candidates");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
