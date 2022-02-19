package io.spring.service.impl;

import io.spring.entity.Product;
import io.spring.repository.ProductRepository;
import io.spring.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }

    @Override
    @Query("SELECT p FROM Product p where p.category.id = ?1")
    public List<Product> getByCategory(Integer id, Pageable pageable) {
        return productRepository.getByCategory(id, pageable);
    }

    @Override
    @Query("SELECT p FROM Product p where p.category.id = ?1")
    public List<Product> getByCategory(Integer id) {
        return productRepository.getByCategory(id);
    }

    @Override
    public boolean existsById(Integer integer) {
        return productRepository.existsById(integer);
    }

    @Override
    @Query("select p from Product p where p.name like ?1")
    public List<Product> getByName(String name, Pageable pageable) {
        return productRepository.getByName(name, pageable);
    }

    @Override
    @Query("select p from Product p where p.name like ?1")
    public List<Product> getByName(String name) {
        return productRepository.getByName(name);
    }
}
