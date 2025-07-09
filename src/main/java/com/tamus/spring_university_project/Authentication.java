package com.tamus.spring_university_project;

import com.tamus.spring_university_project.services.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;

public class Authentication {
    private final UserRepository userService;

    public Authentication(UserRepository userService){
        this.userService = userService;
    }
    public void DescribeUsers(){
        for (User user : userService.getUsers()){
            String hashed = DigestUtils.sha256Hex(user.getPassword());
            System.out.println(hashed);
        }
    }
    public int CheckForUser(String nick){
        for (User user : userService.getUsers()){
            if(user.GetNick().equals(nick)){
                return userService.getUsers().indexOf(user);
            }
        }
        return -1;
    }
    public boolean CheckPassword(String password){
        for (User user : userService.getUsers()){
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}

/*
String value = "haselko";
String sha256hex = DigestUtils.sha256Hex(value);
System.out.println(sha256hex);
 */
