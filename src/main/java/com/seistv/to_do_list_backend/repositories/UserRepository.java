package com.seistv.to_do_list_backend.repositories;

import com.seistv.to_do_list_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
