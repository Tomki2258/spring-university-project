package com.tamus.spring_university_project.repositories.Json;

import com.google.gson.reflect.TypeToken;
import com.tamus.spring_university_project.JsonFileStorage;
import com.tamus.spring_university_project.User;

import java.util.ArrayList;
import java.util.List;

public class UsersJsonRepository {
    private final String USERS_PATH = "src/data/users.json";
    private final JsonFileStorage storage =
            new JsonFileStorage<>(USERS_PATH, new TypeToken<List<User>>(){}.getType());
    private final List<User> users;

    public UsersJsonRepository(){
        this.users = new ArrayList<>(storage.load());
    }
    public List<User> getUsers(){
        return users;
    }
    public void saveUsers(){
        storage.save(this.users);
    }
}
