package com.example.projectboard.model.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ResponseDto {
    private String message;
    private boolean success;
    private Object data;

    public static ResponseDto ok(Object data) {
        return ResponseDto.builder()
                .success(true)
                .message("OK")
                .data(data)
                .build();
    }

    public static ResponseDto ok() {
        return ResponseDto.builder()
                .success(true)
                .message("OK")
                .build();
    }

    public static ResponseDto error(String message) {
        return ResponseDto.builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}
