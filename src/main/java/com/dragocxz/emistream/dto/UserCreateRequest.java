package com.dragocxz.emistream.dto;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String status;
}