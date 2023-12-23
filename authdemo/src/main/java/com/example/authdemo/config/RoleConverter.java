package com.example.authdemo.config;

import com.example.authdemo.model.Role;
import com.example.authdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleConverter implements Converter<String, Role> {

    private RoleRepository repository;

    @Autowired
    public RoleConverter(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role convert(String source) {
        Optional<Role> role = repository.findById(Integer.parseInt(source));
        return role.isPresent() ? role.get() : null;
    }
}
