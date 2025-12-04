package com.primeTradeAssignment.PrimeTradeAssignment.Service;

import com.primeTradeAssignment.PrimeTradeAssignment.Model.User;
import com.primeTradeAssignment.PrimeTradeAssignment.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepo userrepo;
    private final PasswordEncoder passwordencoder;
    UserService(UserRepo userrepo,PasswordEncoder passwordencoder) {
        this.userrepo = userrepo;
        this.passwordencoder = passwordencoder;
    }

    public User registerUser(User user){
        if(userrepo.existsByEmail(user.getEmail())){
            throw new RuntimeException("User is Already Registered");

        }
        user.setPassword(passwordencoder.encode(user.getPassword()));
        User saveUser=userrepo.save(user);
        return saveUser;
    }



}
