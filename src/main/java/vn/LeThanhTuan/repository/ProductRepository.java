package vn.LeThanhTuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.LeThanhTuan.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE category.id = :id")
    List<Product> findAllByCategoryId(Integer id);
}
