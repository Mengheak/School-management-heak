package com.example.school_mgnt_sb.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponseModel {
    private String status;
    private String message;
}
