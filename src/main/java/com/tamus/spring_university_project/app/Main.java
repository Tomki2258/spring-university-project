package com.tamus.spring_university_project.app;


import com.tamus.spring_university_project.AuthService;
import com.tamus.spring_university_project.Authentication;
import com.tamus.spring_university_project.User;
import com.tamus.spring_university_project.services.UserRepository;

import java.util.Scanner;

public class Main {
    public static boolean jsonMode = false;
    public static User loggedUser = null;
    public static void main(String[] args) {
        System.out.println("skibidi");
        /*
        try(Connection connection = JdbcConnectionManager.getInstance().getConnection();
            Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz system \n1:JSON\n2:JDBC");
        String input = scanner.nextLine();
        jsonMode = switch (input) {
            case "2" -> false;
            default -> jsonMode;
        };
        UserRepository userService = new UserRepository();

        Authentication authentication = new Authentication(userService);
        AuthService authService = new AuthService(userService,authentication);
        //String passwordInput = "kox";
        //System.out.println(DigestUtils.sha256Hex(passwordInput));
        System.out.println("1:Logowanie\n2:Rejestracja");
        User user = null;
        int option = scanner.nextInt();
        switch (option){
            case 1:
                user = authService.login();
                break;
            case 2:
                authService.register();
                break;
        }
        if(user == null) return;
        App app = new App(user, userService);

        app.mainProgram();

        userService.save();
    }
}