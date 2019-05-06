package com.r2d2.financeaccount.utils.security.core;

import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


/**
 * Используется чтобы создать UserDetails (PrincipalDetails) объект путем реализации единственного метода этого интерфейса
 */
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username: " + username + " cannot be found!"));
        return new UserPrincipal(person);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new UserPrincipal(person);
    }
}
