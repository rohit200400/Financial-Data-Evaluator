package com.rohit200400.busticketbooking.userservice.controller;

import com.rohit200400.busticketbooking.userservice.dto.GenerateTokenRequestDto;
import com.rohit200400.busticketbooking.userservice.dto.UserRequestDto;
import com.rohit200400.busticketbooking.userservice.entity.User;
import com.rohit200400.busticketbooking.userservice.exception.EmailAlreadyRegisteredException;
import com.rohit200400.busticketbooking.userservice.exception.InvalidCredentialsException;
import com.rohit200400.busticketbooking.userservice.exception.InvalidJwtTokenException;
import com.rohit200400.busticketbooking.userservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody UserRequestDto createUserRequestDto) throws EmailAlreadyRegisteredException {
        User savedUser = authService.registerUser(createUserRequestDto);
        return new ResponseEntity<>(savedUser, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody GenerateTokenRequestDto generateTokenRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(generateTokenRequestDto.getEmail(), generateTokenRequestDto.getPassword()));

            if (authentication.isAuthenticated()) {
                String token = authService.generateToken(generateTokenRequestDto.getEmail());
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                throw new InvalidCredentialsException();
            }
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Authentication failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostAuthorize("returnObject.email == authentication.name")
    public User getUsers(@AuthenticationPrincipal UserDetails userDetails) throws InvalidJwtTokenException {
        String username = userDetails.getUsername();
        return authService.getUsersByUsername(username);
    }


    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> allUsers() throws InvalidJwtTokenException {
        List<User> savedUser = authService.getAllUsers();
        return new ResponseEntity<>(savedUser, HttpStatusCode.valueOf(201));
    }

}
