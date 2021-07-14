package com.javachinna.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javachinna.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<User> findByDisplayName(String username);
}
