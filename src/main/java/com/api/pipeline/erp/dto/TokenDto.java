package com.api.pipeline.erp.dto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String accessToken;
    private String refreshToken;
//    private int expireTime;
//    private int refreshExpireTime;
}