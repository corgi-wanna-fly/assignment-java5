package io.spring.service.impl;

import io.spring.entity.Admin;
import io.spring.repository.AdminRepository;
import io.spring.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Optional<Admin> findById(String s) {
        return adminRepository.findById(s);
    }
}
