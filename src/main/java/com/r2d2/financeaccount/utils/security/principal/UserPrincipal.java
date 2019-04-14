package com.r2d2.financeaccount.utils.security.principal;

import com.r2d2.financeaccount.data.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * предоставляет необходимую информацию для построения объекта Authentication из DAO объектов приложения или
 * других источников данных системы безопасности. Объект UserDetails содержит имя пользователя, пароль,
 * флаги: isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
 * и Collection — прав (ролей) пользователя.
 */
public class UserPrincipal implements UserDetails {
    private final Person person;

    public UserPrincipal(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Person getPerson() {
        return person;
    }
}
