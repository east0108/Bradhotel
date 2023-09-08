package com.hotel.bradhotel.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//接住前端requestbody的參數
public class UserRegisterRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
