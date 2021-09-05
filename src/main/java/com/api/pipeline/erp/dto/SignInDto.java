package com.api.pipeline.erp.dto;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    @NotNull
    private String userId;

    @NotNull
    private String password;
}
