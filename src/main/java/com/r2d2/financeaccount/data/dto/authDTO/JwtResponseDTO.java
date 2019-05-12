package com.r2d2.financeaccount.data.dto.authDTO;

import com.r2d2.financeaccount.data.dto.modelDTO.PersonDTO;
import com.r2d2.financeaccount.data.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {
    private String accessToken;
    private String token = "Bearer";
    private Long user_id;
    private PersonDTO person;

    public JwtResponseDTO() {}

    public JwtResponseDTO(String accessToken, Long user_id) {
        this.accessToken = accessToken;
        this.user_id = user_id;
    }

    public JwtResponseDTO(String accessToken, Long user_id, PersonDTO person) {
        this.accessToken = accessToken;
        this.user_id = user_id;
        this.person = person;
    }
}
