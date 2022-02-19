package io.spring.service;

import io.spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    <S extends Product> S save(S entity);

    Optional<Product> findById(Integer integer);

    void deleteById(Integer integer);

    @Query("SELECT p FROM Product p where p.category.id = ?1")
    List<Product> getByCategory(Integer id, Pageable pageable);

    @Query("SELECT p FROM Product p where p.category.id = ?1")
    List<Product> getByCategory(Integer id);

    boolean existsById(Integer integer);

    @Query("select p from Product p where p.name like ?1")
    List<Product> getByName(String name, Pageable pageable);

    @Query("select p from Product p where p.name like ?1")
    List<Product> getByName(String name);
}
