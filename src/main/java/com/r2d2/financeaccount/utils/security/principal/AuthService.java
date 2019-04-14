package com.r2d2.financeaccount.utils.security.principal;

import com.r2d2.financeaccount.data.dto.authDTO.JwtResponseDTO;
import com.r2d2.financeaccount.data.dto.authDTO.LogInDTO;
import com.r2d2.financeaccount.data.dto.authDTO.RegistrationDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.exception.UniqueViolationException;
import com.r2d2.financeaccount.mapper.OrikaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;


@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private OrikaMapper mapper;

    private PersonRepository repository;

    JwtTokenProvider jwtTokenProvider;

    public AuthService() {
    }

    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                       OrikaMapper mapper, PersonRepository repository,
                       JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    @Transactional(readOnly = true)
    public JwtResponseDTO login(LogInDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtResponseDTO(jwt);
    }

    @Transactional
    public void register(RegistrationDTO registrationDTO) {
        Person person = mapper.map(registrationDTO, Person.class);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRegisterDate(OffsetDateTime.now());
        try {
            this.repository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueViolationException("Username " + person.getUserName() + " already exists");
        }
    }

    public Person getMyself() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getPerson();
    }
}

