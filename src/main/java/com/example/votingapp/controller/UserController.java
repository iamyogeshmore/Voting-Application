package com.example.votingapp.controller;

import com.example.votingapp.Dto.LoginDTO;
import com.example.votingapp.Dto.RegisterDTO;
import com.example.votingapp.Response;
import com.example.votingapp.model.Candidate;
import com.example.votingapp.model.UserModel;
import com.example.votingapp.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserPage")
@CrossOrigin("*")
public class UserController {
    @Autowired
    IuserService iuserService;

    //--------------------------------- Register New User ---------------------------------
    @PostMapping("/Register_User")
    public ResponseEntity<Response> registerUser(@RequestBody RegisterDTO registerDTO) {
        iuserService.RegisterNewUser(registerDTO);
        Response response = new Response(registerDTO, "User Registered Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- User Login ---------------------------------
    @PostMapping("/Login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginDTO loginDTO) {
        int userId = iuserService.login(loginDTO);
        Response response = new Response(userId, "Login Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- User Logout ---------------------------------
    @PostMapping("/Logout")
    public ResponseEntity<Response> logoutUser(@RequestParam int userID) {
        iuserService.logoutUser(userID);
        Response response = new Response("Logout", "Logout Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- User Vote For Candidate ---------------------------------
    @PostMapping("/Vote")
    public ResponseEntity<Response> voting(@RequestParam int userId, @RequestParam int candidateID) {
        iuserService.Vote(userId, candidateID);
        Response response = new Response(candidateID, "voting Successfully for");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- User Data ---------------------------------
    @GetMapping("/UserData")
    public ResponseEntity<Response> UserData(@RequestParam int userId) {
        UserModel userModel = iuserService.fetchUserData(userId);
        Response response = new Response(userModel, "UserData");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //---------------------------------  Show All Candidate for voting ---------------------------------
    @GetMapping("/Show_All_Candidates")
    public ResponseEntity<Response> getCandidateData() {
        List<Candidate> candidateList = iuserService.showAllCandiates();
        Response response = new Response(candidateList, "We have " + candidateList.size() + " candidates");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
