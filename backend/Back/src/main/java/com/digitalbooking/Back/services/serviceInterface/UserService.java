package com.digitalbooking.Back.services.serviceInterface;


import com.digitalbooking.Back.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    Optional<User> searchUserById(Integer id);
    List<User> listUser();
    User updateUser(User user);
    void deleteUser(Integer id);
}
