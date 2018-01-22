package com.anteastra.devopsbuddy.integration;

import com.anteastra.devopsbuddy.backend.persistence.domain.backend.Role;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.User;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.anteastra.devopsbuddy.backend.service.UserService;
import com.anteastra.devopsbuddy.enums.PlansEnum;
import com.anteastra.devopsbuddy.enums.RolesEnum;
import com.anteastra.devopsbuddy.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractServiceIntegrationTest {
    @Autowired
    protected UserService userService;

    protected User createUser(TestName testName) {
        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UserUtils.createBasicUser(username, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        return userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
    }
}