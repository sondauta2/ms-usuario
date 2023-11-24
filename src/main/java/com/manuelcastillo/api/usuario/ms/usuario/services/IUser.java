package com.manuelcastillo.api.usuario.ms.usuario.services;

import com.manuelcastillo.api.usuario.ms.usuario.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUser {
    User create(User user);

    List<User> getUser();

    Optional<User> getByEmail(String email);

    Optional<User> getById(UUID id);

    Optional<User> getValidLogin(String email, String password);
}
