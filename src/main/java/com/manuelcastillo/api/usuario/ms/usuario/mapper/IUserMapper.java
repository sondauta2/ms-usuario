package com.manuelcastillo.api.usuario.ms.usuario.mapper;

import com.manuelcastillo.api.usuario.ms.usuario.dto.OutLoginDto;
import com.manuelcastillo.api.usuario.ms.usuario.dto.UserDto;
import com.manuelcastillo.api.usuario.ms.usuario.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserMapper {

    UserDto addUserAndPhone(UserDto userDto);

    Object createUser(UserDto userDto);

    User delUser(UUID id);

    OutLoginDto loginUser(String email, String password);

    Optional<User> getUserById(UUID id);

}
