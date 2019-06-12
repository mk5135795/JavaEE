package com.jee.service;

import com.jee.model.User;

public interface UserService {
  
 public User findUserByLogin(String login);
 public void saveUser(User user);
}