package com.hsbc.dao;

import com.hsbc.beans.User;

public interface UserDao {
    User authenticate(String username, String password);
    void addUser(User user);
}
