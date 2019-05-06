package com.r2d2.financeaccount.utils.security.core;

import com.r2d2.financeaccount.data.dto.authDTO.JwtResponseDTO;
import com.r2d2.financeaccount.data.dto.authDTO.LogInDTO;
import com.r2d2.financeaccount.data.dto.authDTO.RegistrationDTO;
import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import com.r2d2.financeaccount.data.repository.RoleRepository;
import com.r2d2.financeaccount.exception.ApiException;
import com.r2d2.financeaccount.exception.UniqueViolationException;
import com.r2d2.financeaccount.mapper.OrikaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Collections;


@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrikaMapper mapper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    @Transactional
    public JwtResponseDTO login(LogInDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new JwtResponseDTO(jwt, principal.getPerson().getId());
    }

    @Transactional
    public void register(RegistrationDTO registrationDTO) {
        Person person = mapper.map(registrationDTO, Person.class);
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ApiException("User Role not set."));

        person.setRoles(Collections.singleton(userRole));


        person.setRegisterDate(OffsetDateTime.now());
        try {
            this.personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueViolationException("Username " + person.getUsername() + " already exists");
        }
    }

    public Person getMyself() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getPerson();
    }
}

