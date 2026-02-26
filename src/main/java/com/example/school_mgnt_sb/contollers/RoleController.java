package com.example.school_mgnt_sb.contollers;

import com.example.school_mgnt_sb.dto.role.CreateRoleDto;
import com.example.school_mgnt_sb.dto.role.RoleDto;
import com.example.school_mgnt_sb.entities.auth.Role;
import com.example.school_mgnt_sb.mapper.RoleMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping
    public  ResponseEntity<BaseResponseModel> createRole(@RequestBody @Valid CreateRoleDto dto){
        return this.roleService.createRole(dto);
    }

    @GetMapping
    public ResponseEntity<BaseResponseModel> getAllRoles(){
        return this.roleService.getAllRoles();
    }
}
