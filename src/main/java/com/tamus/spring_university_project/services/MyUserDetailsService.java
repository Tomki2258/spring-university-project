package com.tamus.spring_university_project.services;

import com.tamus.spring_university_project.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByNick(username);
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole().toString()));
        var authorities =
                user.getRoles().stream()
                        .map(role -> new
                                SimpleGrantedAuthority(role.getName()))
                        .toList();
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                authorityList
        );
    }
}
