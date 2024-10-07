package com.tutorial.api.mvc.api.repositories;

import com.tutorial.api.mvc.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<User, String> {
}
