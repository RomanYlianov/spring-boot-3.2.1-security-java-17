package com.example.restsequrity.config;

import com.example.restsequrity.model.AppUser;
import com.example.restsequrity.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public class UserConverter implements Converter<String, AppUser> {


    private AppUserRepository repository;

    @Autowired
    public UserConverter(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppUser convert(String source) {
        Optional<AppUser> userBox = repository.findById(Long.parseLong(source));
        return userBox.isPresent() ? userBox.get() : null;
    }
}
