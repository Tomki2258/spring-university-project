package com.tamus.spring_university_project.services;

import com.tamus.spring_university_project.User;
import com.tamus.spring_university_project.dto.UserRequest;

import java.util.Optional;

public interface IUserService {
    void register(UserRequest req);
    Optional<User> findByLogin(String login);
}