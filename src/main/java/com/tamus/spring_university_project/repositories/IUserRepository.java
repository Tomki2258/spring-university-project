package com.tamus.spring_university_project.repositories;


import com.tamus.spring_university_project.User;

import java.util.List;

public interface IUserRepository {
    User getUser(int index);
    List<User> getUsers();
    void save();
    void add(User user);
    boolean userExist(String nick);
    void saveJson();
}
