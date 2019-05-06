package com.r2d2.financeaccount.data.repository;

import com.r2d2.financeaccount.utils.security.core.Role;
import com.r2d2.financeaccount.utils.security.core.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
