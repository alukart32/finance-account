package com.r2d2.financeaccount.data.dto.authDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {
    private String accessToken;
    private String token = "Bearer";
    private Long user_id;

    public JwtResponseDTO() {}

    public JwtResponseDTO(String accessToken, Long user_id) {
        this.accessToken = accessToken;
        this.user_id = user_id;
    }
}
