package com.tamus.spring_university_project;

import com.tamus.spring_university_project.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;
import java.util.UUID;

public class AuthService {
    private Authentication authentication;
    private UserService userService;

    public AuthService(UserService userService, Authentication authentication) {
        this.authentication = authentication;
        this.userService = userService;
    }

    public User login() {

        Scanner scanner = new Scanner(System.in);
        String loginInput = scanner.nextLine();

        int result = authentication.CheckForUser(loginInput);
        if (result == -1) {
            System.out.println("BŁĄD LOGOWANIA");
            return null;
        }
        String passwordInput = scanner.nextLine();

        boolean passwordResult = false;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode(passwordInput);
        //passwordResult = authentication.CheckPassword(DigestUtils.sha256Hex(passwordInput));
        passwordResult = authentication.CheckPassword(hashed);
        //String hashed = BCrypt.hashpw(passwordInput, BCrypt.gensalt());

        //passwordResult = authentication.CheckPassword(passwordInput);

        if (!passwordResult) {
            System.out.println("BŁĄD LOGOWANIA");
            return null;
        }

        return userService.getUser(result);
    }

    public boolean register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");
        String loginString = scanner.nextLine();

        if (userService.userExist(loginString)) {
            System.out.println("TAKI UZYTKOWNIK ISTNIEJE");
            return false;
        }

        System.out.println("Haslo");
        String passwordInput = scanner.nextLine();
        if (passwordInput.equals("")) {
            System.out.println("HASLO NIE MOZE BYC PUSTE");
            return false;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String hashed = DigestUtils.sha256Hex(passwordInput);
        String hashed = encoder.encode(passwordInput);
        UUID uuid = UUID.randomUUID();

        User user = new User(uuid.toString(), loginString, hashed);
        userService.add(user);
        userService.save();
        return true;
    }
}
