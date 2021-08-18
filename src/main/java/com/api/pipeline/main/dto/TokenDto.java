package com.api.pipeline.main.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String access_token;
    private String refresh_token;
}
