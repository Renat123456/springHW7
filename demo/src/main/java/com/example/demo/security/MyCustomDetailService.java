package com.example.demo.security;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyCustomDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public MyCustomDetailService(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        List<Long> userRoles = userRoleRepository.findUserRolesByUserId(user.getId());
        List<String> userRoleNames = new ArrayList<>();
        for (int i = 0; i < userRoles.size(); i++) {
            userRoleNames.add(roleRepository.findNameById(userRoles.get(i)));
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = userRoleNames.stream().map(it -> new SimpleGrantedAuthority(it)).toList();

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), simpleGrantedAuthorities);
    }
}
