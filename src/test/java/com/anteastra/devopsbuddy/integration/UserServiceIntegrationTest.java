package com.anteastra.devopsbuddy.integration;

import com.anteastra.devopsbuddy.DevopsbuddyApplication;
import com.anteastra.devopsbuddy.backend.persistence.domain.backend.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testCreateNewUser() throws Exception {

        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

    }

}