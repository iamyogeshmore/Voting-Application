package com.example.votingapp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String emailId;
    private String phoneNo;
    private String password;
    private String role = "user";
    private boolean isLogin = false;
    private boolean isVoted = false;

    public UserModel(String userName, String emailId, String phoneNo, String password) {
        this.userName = userName;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.password = password;
    }
}
