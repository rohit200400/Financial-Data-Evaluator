package com.rohit200400.busticketbooking.userservice.service;

import com.rohit200400.busticketbooking.userservice.dto.UserRequestDto;
import com.rohit200400.busticketbooking.userservice.entity.User;
import com.rohit200400.busticketbooking.userservice.entity.UserRole;
import com.rohit200400.busticketbooking.userservice.exception.EmailAlreadyRegisteredException;
import com.rohit200400.busticketbooking.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User registerUser(UserRequestDto userDetails) throws EmailAlreadyRegisteredException {
        // Check if the user with the given email already exists
        if (userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException();
        }

        User newUser = new User();
        newUser.setEmail(userDetails.getEmail());
        newUser.setName(userDetails.getName());

        // Encoding the password before saving it to the database
        newUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));

        // Setting a default role
        newUser.setRole(UserRole.ROLE_USER);

        // Saving the user to the database
        return userRepository.save(newUser);
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUsersByUsername(String email) {
        return  userRepository.findByEmail(email).orElse(null);
    }
}
