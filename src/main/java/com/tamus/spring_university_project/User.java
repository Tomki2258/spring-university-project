package com.tamus.spring_university_project;

import com.tamus.spring_university_project.models.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String login;
    private String password;
    private int rendedVehicle;
    private UserType role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    @Getter
    private Set<Role> roles = new HashSet<>();

    public User(String nick, String password, UserType userType, int rendedVehicle) {
        this.login = nick;
        this.password = password;
        this.role = userType;
        this.rendedVehicle = rendedVehicle;
    }
    public User(String id,String nick, String password){
        this.id = id;
        this.login = nick;
        this.password = password;
        role = UserType.USER;
        rendedVehicle = -1;
    }
    public User(String uid,String nick,String password,String role){
        this.id = uid;
        this.login = nick;
        this.password = password;
        this.role = UserType.valueOf(role);
    }

    public void RentVehicle(int vehicle) {
        rendedVehicle = vehicle;
    }

    public void RemoveVehicle() {
        rendedVehicle = -1;
    }

    public int GetRendedVehicle() {
        return rendedVehicle;
    }
    public UserType GetUserType(){
        return role;
    }
    public void Describeuser(){
        log.info(this.id + " " +this.login + " " + this.password + " " + this.role);
    }
    public String GetNick(){
        return login;
    }
    public String getPassword(){
        return password;
    }
    public String getId(){
        return  id;
    }

}
