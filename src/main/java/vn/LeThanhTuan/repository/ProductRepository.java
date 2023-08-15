package vn.LeThanhTuan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.LeThanhTuan.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE category.id = :id")
    List<Product> findAllByCategoryId(Integer id);

    @Query("SELECT p FROM Product p WHERE p.active = true AND (p.id LIKE %?1% OR p.name LIKE %?1% OR p.description LIKE %?1% OR p.price like %?1% OR p.category.name LIKE %?1%)")
    Page<Product> findAllActiveProduct(String keyword, Pageable pageable);
}
