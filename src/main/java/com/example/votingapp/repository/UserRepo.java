package com.example.votingapp.repository;

import com.example.votingapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
    UserModel findByEmailId(String emilId);

    UserModel findByEmailIdAndPassword(String email, String password);
}
