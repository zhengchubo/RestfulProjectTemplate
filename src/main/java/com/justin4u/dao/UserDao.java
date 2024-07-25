package com.justin4u.dao;

import com.justin4u.entity.User;

import java.util.List;

public interface UserDao {

    int insertUser(User user);

    User findUserById(int userId);

    List<User> findAllUsers();

    boolean deleteUser(int userId);

    void updateUser(User user);

}
