package com.example.authdemo.config;

import com.example.authdemo.model.User;
import com.example.authdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter implements Converter<String, User> {

    private UserRepository repository;

    @Autowired
    public UserConverter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User convert(String source) {
        Optional<User> user = repository.findById(Integer.parseInt(source));
        return user.isPresent()? user.get() : null;
    }
}
