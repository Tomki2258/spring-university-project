package com.tamus.spring_university_project.services;

import com.tamus.spring_university_project.User;
import com.tamus.spring_university_project.repositories.Jdbc.UserJdbcRepository;
import com.tamus.spring_university_project.repositories.Json.UsersJsonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tamus.spring_university_project.app.Main.jsonMode;

@Slf4j
@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private final UsersJsonRepository usersJsonRepository = new UsersJsonRepository();
    private final UserJdbcRepository userJdbcRepository = new UserJdbcRepository();
    public UserService(){
        users = userJdbcRepository.getUsers();
        log.info("Reading users.....");
        for(User user:users){
            user.Describeuser();
        }
        log.info("READING DONE");
    }
    public User getUser(int index) {
        return users.get(index);
    }

    public User getUserByNick(String nickname) {
        for (User user : users) {
            if (user.GetNick().equals(nickname)) {
                return user;
            }
        }
        return null;
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
