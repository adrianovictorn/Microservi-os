package io.github.adrianovictorn.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.adrianovictorn.user.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}