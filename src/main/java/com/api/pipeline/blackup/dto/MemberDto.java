package com.api.pipeline.blackup.dto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String id;

    private String password;

//    private String nickname;
}