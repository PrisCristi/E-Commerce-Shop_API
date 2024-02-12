package com.ecomerceApi.Priscila;

import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class UserRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Test
    public void whenFindByUsername_returnUser() {

        User user = new User("priscila", "priscila@email.com");
        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.findByUsername(
                user.getUsername()).orElseThrow(null);

    }
}
