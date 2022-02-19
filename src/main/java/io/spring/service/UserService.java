package io.spring.service;

import io.spring.dto.UserLogin;
import io.spring.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    <S extends User> S save(S entity);

    Optional<User> findById(Integer integer);

    @Query("select u from User u where u.email = ?1 and u.password = ?2")
    Optional<User> findByEmail(String email, String password);

    @Query("select u from User u where u.email = ?1")
    Optional<User> getByEmail(String email);
}
