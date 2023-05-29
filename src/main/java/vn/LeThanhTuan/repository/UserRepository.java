package vn.LeThanhTuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.LeThanhTuan.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
