package com.r2d2.financeaccount.controller.auth;


import com.r2d2.financeaccount.data.dto.authDTO.JwtResponseDTO;
import com.r2d2.financeaccount.data.dto.authDTO.LogInDTO;
import com.r2d2.financeaccount.data.dto.authDTO.RegistrationDTO;
import com.r2d2.financeaccount.data.dto.modelDTO.PersonDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.mapper.OrikaMapper;
import com.r2d2.financeaccount.utils.security.core.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    private OrikaMapper mapper;
    private PersonRepository personRepository;

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService, OrikaMapper orikaMapper, PersonRepository personRepository) {
        this.authService = authService;
        this.mapper = orikaMapper;
        this.personRepository = personRepository;
    }

    @RequestMapping(path = "/login", method = POST)
    public ResponseEntity<JwtResponseDTO> login(@RequestBody @Valid LogInDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @RequestMapping(path = "/register", method = POST)
    public void registration(@RequestBody @Valid RegistrationDTO registrationDTO) {
        authService.register(registrationDTO);
    }
}
