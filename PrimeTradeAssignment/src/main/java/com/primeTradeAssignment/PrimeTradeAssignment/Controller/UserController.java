package com.primeTradeAssignment.PrimeTradeAssignment.Controller;

import com.primeTradeAssignment.PrimeTradeAssignment.DTOs.LoginRequest;
import com.primeTradeAssignment.PrimeTradeAssignment.DTOs.LoginResponse;
import com.primeTradeAssignment.PrimeTradeAssignment.Model.User;
import com.primeTradeAssignment.PrimeTradeAssignment.Repository.UserRepo;
import com.primeTradeAssignment.PrimeTradeAssignment.Security.JwtUtil;
import com.primeTradeAssignment.PrimeTradeAssignment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {




    private final UserService service;

    private final AuthenticationManager authManager;

    private final UserRepo userRepo;

    private final JwtUtil jwtUtil;

    public UserController(UserService service, AuthenticationManager authManager, UserRepo userRepo, JwtUtil jwtUtil) {
        this.service = service;
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User saved = service.registerUser(user);
        return ResponseEntity.ok(saved);
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        // Authenticating using email + password
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        //  Setting authentication in context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //  Fetching the user from DB
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Generating JWT Token
        String jwtToken = jwtUtil.generateToken(user.getEmail(), user.getRole());

        //  Returning response
        return ResponseEntity.ok(new LoginResponse(jwtToken, user.getEmail(), user.getRole().name()));
    }
}
