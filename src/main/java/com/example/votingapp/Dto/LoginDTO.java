package com.example.votingapp.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginDTO {
    private String emailId;
    private String password;
}
