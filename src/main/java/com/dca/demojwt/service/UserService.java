package com.dca.demojwt.service;

import com.dca.demojwt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(String id);
    void deleteById(String id);
    User update( String id,  User user);

}
