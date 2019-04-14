package com.r2d2.financeaccount.controller.auth;


import com.r2d2.financeaccount.data.dto.authDTO.JwtResponseDTO;
import com.r2d2.financeaccount.data.dto.authDTO.LogInDTO;
import com.r2d2.financeaccount.data.dto.authDTO.RegistrationDTO;
import com.r2d2.financeaccount.utils.security.principal.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(path = "/login", method = POST)
    public JwtResponseDTO login(@RequestBody @Valid LogInDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @RequestMapping(path = "/register", method = POST)
    public void registration(@RequestBody @Valid RegistrationDTO registrationDTO) {
        authService.register(registrationDTO);
    }
}
