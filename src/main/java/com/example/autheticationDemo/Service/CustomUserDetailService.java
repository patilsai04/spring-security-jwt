package com.example.autheticationDemo.Service;

import com.example.autheticationDemo.Entity.Permission;
import com.example.autheticationDemo.Entity.User;
import com.example.autheticationDemo.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(
                new SimpleGrantedAuthority(
                        "ROLE_"+user.getRole().getName()
                )
        );
        for(Permission permission:user.getRole().getPermission()){
            authorities.add(
                    new SimpleGrantedAuthority(
                            permission.getName()
                    )
            );
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }


}
