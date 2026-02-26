package com.example.school_mgnt_sb.mapper;

import com.example.school_mgnt_sb.dto.role.RoleDto;
import com.example.school_mgnt_sb.entities.auth.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    public Role toEntity(RoleDto dto){
        Role ent  = new Role();
        ent.setName(ent.getName());
        return ent;
    }
    public RoleDto toDto(Role entity){
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    public List<RoleDto> toDtoList(List<Role> entities){
        return entities.stream().map(this::toDto).toList();
    }
}
