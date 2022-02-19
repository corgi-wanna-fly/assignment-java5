package io.spring.service;

import io.spring.entity.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findById(String s);
}
