package com.example.school_mgnt_sb.services.auth;

import com.example.school_mgnt_sb.dto.CreateUserAccountDto;
import com.example.school_mgnt_sb.dto.auth.LoginDto;
import com.example.school_mgnt_sb.dto.auth.LoginResponseDto;
import com.example.school_mgnt_sb.entities.auth.UserAccount;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.models.AuthUser;
import com.example.school_mgnt_sb.repositories.AuthRepository;
import com.example.school_mgnt_sb.repositories.RoleRepository;
import com.example.school_mgnt_sb.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public List<UserAccount> getUser(){
       return this.authRepository.findAll();
    }


    public UserAccount create(CreateUserAccountDto dto) {

        if(authRepository.existsByEmail(dto.getEmail())){
            throw new StatusException(HttpStatus.CONFLICT,"User with email = "+dto.getEmail()+" already exists");
        }

        UserAccount user = UserAccount.builder()
                .email(dto.getEmail())
                .passwordHash(passwordEncoder.encode(dto.getPassword()))
                .enabled(true)
                .roles(new HashSet<>(roleRepository.findAllById(dto.getRoleIds())))
                .build();

        return authRepository.save(user);
    }

    public LoginResponseDto login(LoginDto dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
            );
            AuthUser user = (AuthUser) auth.getPrincipal();
            String token = jwtService.generateAccessToken(user);
            return new LoginResponseDto(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
