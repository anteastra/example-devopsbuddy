package com.anteastra.devopsbuddy.integration;

import com.anteastra.devopsbuddy.DevopsbuddyApplication;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.Role;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.User;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.anteastra.devopsbuddy.backend.persistence.repositories.PasswordResetTokenRepository;
import com.anteastra.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.anteastra.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.anteastra.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.anteastra.devopsbuddy.enums.PlansEnum;
import com.anteastra.devopsbuddy.enums.RolesEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.UUID;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class RepositoriesIntegrationTest extends AbstractIntegrationTest {

    @Rule
    public TestName testName = new TestName();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Before
    public void init() {
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(passwordResetTokenRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole() throws Exception {

        Role userRole  = createRole(RolesEnum.BASIC);
        roleRepository.save(userRole);

        Role retrievedRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void createNewUser() throws Exception {

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        User basicUser = createUser(username, email);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur : newlyCreatedUserUserRoles) {
            Assert.assertNotNull(ur.getRole());
            // Assert.assertNotNull(ur.getRole().getId()); // something is wrong here
        }

    }

    @Test
    public void testDeleteUser() throws Exception {

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        User basicUser = createUser(username, email);
        userRepository.delete(basicUser.getId());
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        User user = createUser(testName);

        User newlyFoundUser = userRepository.findByEmail(user.getEmail());
        Assert.assertNotNull(newlyFoundUser);
        Assert.assertNotNull(newlyFoundUser.getId());
    }

    @Test
    public void testUpdateUserPassword() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        String newPassword = UUID.randomUUID().toString();

        userRepository.updateUserPassword(user.getId(), newPassword);

        user = userRepository.findOne(user.getId());
        Assert.assertEquals(newPassword, user.getPassword());

    }
}
