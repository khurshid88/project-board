package com.example.projectboard.model.dto;

import com.example.projectboard.model.entity.Post;
import com.example.projectboard.model.entity.UserAccount;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CommentReq {

    @NotBlank(message = "userId is required")
    private String userId;

    @NotBlank(message = "content is required")
    @Size(min = 5, max = 500)
    private String content;

}
