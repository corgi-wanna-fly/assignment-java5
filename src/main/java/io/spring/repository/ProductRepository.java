package io.spring.repository;

import io.spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p where p.category.id = ?1")
    public List<Product> getByCategory(Integer id, Pageable pageable);

    @Query("SELECT p FROM Product p where p.category.id = ?1")
    public List<Product> getByCategory(Integer id);

    @Query("select p from Product p where p.name like ?1")
    public List<Product> getByName(String name, Pageable pageable);

    @Query("select p from Product p where p.name like ?1")
    public List<Product> getByName(String name);
}