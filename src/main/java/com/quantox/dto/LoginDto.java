package com.quantox.dto;

import javax.validation.constraints.NotNull;

public class LoginDto {
    @NotNull
    private String userName;
    @NotNull
    private String password;

    public LoginDto() {
    }

    public LoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
