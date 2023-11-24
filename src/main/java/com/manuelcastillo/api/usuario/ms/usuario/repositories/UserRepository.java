package com.manuelcastillo.api.usuario.ms.usuario.repositories;

import com.manuelcastillo.api.usuario.ms.usuario.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

}
