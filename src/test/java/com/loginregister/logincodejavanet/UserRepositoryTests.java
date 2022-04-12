package com.loginregister.logincodejavanet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    // test methods go below

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("alex@gmail.com");
        user.setPassword("alex4020");
        user.setFirstName("Alex");
        user.setLastName("Ka");
        user.setAddress("BadiduColony");
        user.setPhoneNumber("5465484987");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    public void testFindUserByEmail() {

        String email = "ravikumar@gmail.com";
        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }
}