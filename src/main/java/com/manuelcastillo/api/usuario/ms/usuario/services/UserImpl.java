package com.manuelcastillo.api.usuario.ms.usuario.services;

import com.manuelcastillo.api.usuario.ms.usuario.entities.User;
import com.manuelcastillo.api.usuario.ms.usuario.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserImpl implements IUser{

    @Autowired
    UserRepository repository;
    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getUser() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getByEmail(String email){
        return Optional.ofNullable(repository.findByEmail(email));
    }

    @Override
    public Optional<User> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> getValidLogin(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }


}
