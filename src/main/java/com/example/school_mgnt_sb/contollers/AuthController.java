package com.example.school_mgnt_sb.contollers;

import com.example.school_mgnt_sb.dto.CreateUserAccountDto;
import com.example.school_mgnt_sb.dto.auth.LoginDto;
import com.example.school_mgnt_sb.dto.auth.LoginResponseDto;
import com.example.school_mgnt_sb.dto.base.Response;
import com.example.school_mgnt_sb.entities.auth.UserAccount;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<BaseResponseModel> getUsers(){
        List<UserAccount> user = authService.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<List<UserAccount>>("success", "get user accounts successfully", user));
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createUserAccount(@RequestBody @Valid CreateUserAccountDto dto){
        UserAccount newUser = authService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "Created", newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginDto dto){
        System.out.println("login hit");
        LoginResponseDto responseDto = this.authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200", "success", "successfully logged in", responseDto));
    }
}
