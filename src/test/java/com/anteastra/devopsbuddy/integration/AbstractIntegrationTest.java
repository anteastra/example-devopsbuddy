package com.anteastra.devopsbuddy.integration;

import com.anteastra.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.Role;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.User;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.anteastra.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.anteastra.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.anteastra.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.anteastra.devopsbuddy.enums.PlansEnum;
import com.anteastra.devopsbuddy.enums.RolesEnum;
import com.anteastra.devopsbuddy.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractIntegrationTest {

    @Autowired
    protected PlanRepository planRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;

    protected Plan createPlan(PlansEnum plansEnum) {
        Plan plan = new Plan();
        plan.setId(plansEnum.getId());
        plan.setName(plansEnum.getPlanName());
        return plan;
    }

    protected Role createRole(RolesEnum rolesEnum) {
        return new Role(rolesEnum);
    }

    protected User createUser(String username, String email) {
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UserUtils.createBasicUser(username, email);
        basicUser.setPlan(basicPlan);

        Role basicRole = createRole(RolesEnum.BASIC);
        roleRepository.save(basicRole);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRepository.save(basicUser);
        return basicUser;
    }

    protected User createUser(TestName testName) {
        return createUser(testName.getMethodName(), testName.getMethodName() + "@devopsbuddy.com");
    }
}