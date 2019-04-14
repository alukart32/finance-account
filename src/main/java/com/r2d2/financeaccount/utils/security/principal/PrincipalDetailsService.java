package com.r2d2.financeaccount.utils.security.principal;

import com.r2d2.financeaccount.data.model.Person;
import com.r2d2.financeaccount.data.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


/**
 * Используется чтобы создать UserDetails (PrincipalDetails) объект путем реализации единственного метода этого интерфейса
 */
public class PrincipalDetailsService implements UserDetailsService {
    PersonRepository personRepository;

    public PrincipalDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User with username: " + username + " cannot be found!"));
        return new UserPrincipal(person);
    }
}
