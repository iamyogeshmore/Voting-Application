package com.example.votingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class VotingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingAppApplication.class, args);
        System.out.println("Welcome to Voting App");
    }

}
