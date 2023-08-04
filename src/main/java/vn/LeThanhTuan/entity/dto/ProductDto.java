package vn.LeThanhTuan.entity.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.LeThanhTuan.entity.Category;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private double price;

    private boolean active;

    private String image;

    private Integer categoryId;
}
