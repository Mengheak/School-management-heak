package com.example.school_mgnt_sb.models;



public class ApiResponseModel<T> extends BaseResponseModel{
    public T data;
    public ApiResponseModel(String status, String message, T data){
        super(status, message);
        this.data = data;
    }
}
