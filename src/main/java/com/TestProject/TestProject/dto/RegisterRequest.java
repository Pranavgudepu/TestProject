package com.TestProject.TestProject.dto;

import com.TestProject.TestProject.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String userName;
    private String password;
    private String email;
    private Role role;

}
