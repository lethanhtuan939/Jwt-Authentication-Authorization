package vn.LeThanhTuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.LeThanhTuan.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
