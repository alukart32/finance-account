package com.r2d2.financeaccount.bootdata;

import com.r2d2.financeaccount.data.repository.RoleRepository;
import com.r2d2.financeaccount.utils.security.core.Role;
import com.r2d2.financeaccount.utils.security.core.RoleName;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    RoleRepository roleRepository;

    public RoleBootstrap(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        roleRepository.saveAll(getRoles());
    }

    private Set<Role> getRoles(){
        Set<Role> roles = new HashSet<>();

        Role role_user = new Role(RoleName.ROLE_USER);

        roles.add(role_user);

        return roles;
    }
}