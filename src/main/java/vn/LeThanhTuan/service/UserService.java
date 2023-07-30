package vn.LeThanhTuan.service;

import vn.LeThanhTuan.entity.dto.UserDto;

public interface UserService {
    UserDto updateUser(UserDto userDto, Integer id);

    UserDto disabledUserById(Integer id);

    UserDto enabledUserById(Integer id);
}
