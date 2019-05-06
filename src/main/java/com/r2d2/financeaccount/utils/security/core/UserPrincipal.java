package com.r2d2.financeaccount.utils.security.core;

import com.r2d2.financeaccount.data.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        this.authorities = getAuthorities();
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = person.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Person getPerson() {
        return person;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
