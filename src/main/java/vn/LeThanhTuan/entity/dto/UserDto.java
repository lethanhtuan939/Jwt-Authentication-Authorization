package vn.LeThanhTuan.entity.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public boolean active;
}
