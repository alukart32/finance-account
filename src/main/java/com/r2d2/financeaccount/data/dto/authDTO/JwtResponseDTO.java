package com.r2d2.financeaccount.data.dto.authDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtResponseDTO() {}

    public JwtResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
