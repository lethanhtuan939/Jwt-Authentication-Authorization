package vn.LeThanhTuan.entity.dto;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.LeThanhTuan.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;

    private String name;

    private boolean active;

    List<Product> products = new ArrayList<>();
}
