package com.example.restsequrity.serivce.impl;

import com.example.restsequrity.model.AppUser;
import com.example.restsequrity.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepository repository;

    @Autowired
    public void setRepository(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userBox = repository.findByUserName(username);
        AppUser u = userBox.get();

        //Set<GrantedAuthority> authorities = new HashSet<>();
        //authorities.add(u.getRole());

        return userBox.isPresent() ? userBox.get() : null;
    }
}
