package com.tamus.spring_university_project.services;

import com.tamus.spring_university_project.User;
import com.tamus.spring_university_project.repositories.Jdbc.UserJdbcRepository;
import com.tamus.spring_university_project.repositories.Json.UsersJsonRepository;

import java.util.ArrayList;
import java.util.List;

import static com.tamus.spring_university_project.app.Main.jsonMode;


public class UserService {

    private List<User> users = new ArrayList<>();
    private final UsersJsonRepository usersJsonRepository = new UsersJsonRepository();
    private final UserJdbcRepository userJdbcRepository = new UserJdbcRepository();
    public UserService(){
        if(jsonMode) {
            users = usersJsonRepository.getUsers();
        }
        else{
            users = userJdbcRepository.getUsers();
        }

        for(User user:users){
            user.Describeuser();
        }
    }
    public User getUser(int index) {
        return users.get(index);
    }

    public List<User> getUsers() {
        return users;
    }

    public void add(User user) {
        if(jsonMode){
            usersJsonRepository.saveUsers();
        }else{
            userJdbcRepository.add(user);
        }
        users.add(user);
        save();
    }
    public void save(){
        if(jsonMode){
            usersJsonRepository.saveUsers();
        }else{
            userJdbcRepository.save();
        }
    }
    public boolean userExist(String nick) {
        for (User user: users){
            if(user.GetNick().equals(nick)){
                return true;
            }
        }
        return false;
    }
}
