package io.spring.service.impl;

import io.spring.dto.UserLogin;
import io.spring.entity.User;
import io.spring.repository.UserRepository;
import io.spring.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    @Query("select u from User u where u.email = ?1 and u.password = ?2")
    public Optional<User> findByEmail(String email, String password) {
        return userRepository.findByEmail(email, password);
    }

    @Override
    @Query("select u from User u where u.email = ?1")
    public Optional<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
