package com.example.school_mgnt_sb.services;

import com.example.school_mgnt_sb.dto.role.CreateRoleDto;
import com.example.school_mgnt_sb.entities.auth.Role;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.mapper.RoleMapper;
import com.example.school_mgnt_sb.models.ApiResponseModel;
import com.example.school_mgnt_sb.models.BaseResponseModel;
import com.example.school_mgnt_sb.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public ResponseEntity<BaseResponseModel> createRole(CreateRoleDto dto){
        if(roleRepository.existsByName(dto.getName())){
            throw new StatusException(HttpStatus.CONFLICT, "Role is already exist with value = " + dto.getName());
        }
        Role newRole = Role.builder().name(dto.getName()).build();
       return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseModel<>("success", "role has been created successfully", newRole));
    }

    public ResponseEntity<BaseResponseModel> getAllRoles(){
       return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get all roles successfully", roleMapper.toDtoList(this.roleRepository.findAll())));
    }
    public ResponseEntity<BaseResponseModel> getOneRole(UUID id){
        Role fetchedRole = roleRepository.findById(id).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Can't get role with id = "+id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get one role successfully", fetchedRole));
    }
}
