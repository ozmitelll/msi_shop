package com.msi.repositories;

import com.msi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findUsersByUsername(String login);
    User findUsersByEmail(String email);
    boolean existsByUsername(String username);

}
