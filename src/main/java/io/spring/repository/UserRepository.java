package io.spring.repository;

import io.spring.dto.UserLogin;
import io.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.email = ?1 and u.password = ?2")
    public Optional<User> findByEmail(String email, String password);

    @Query("select u from User u where u.email = ?1")
    public Optional<User> getByEmail(String email);
}