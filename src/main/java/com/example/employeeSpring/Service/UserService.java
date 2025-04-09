package com.example.employeeSpring.Service;

import com.example.employeeSpring.Model.User;
import com.example.employeeSpring.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User register(User user){
        return userRepo.save(user);
    }


}
