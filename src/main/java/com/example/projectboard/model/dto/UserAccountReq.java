package com.example.projectboard.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class UserAccountReq {

    @NotBlank(message = "title is required")
    @Size(min = 5, max = 20)
    private String userId;

    @NotBlank(message = "title is required")
    @Size(min = 5, max = 20)
    private String userPassword;

    @NotBlank(message = "title is required")
    @Email
    private String email;

    @NotBlank(message = "title is required")
    @Size(min = 5, max = 20)
    private String nickname;

    @NotBlank(message = "title is required")
    @Size(min = 5, max = 100)
    private String memo;

}
