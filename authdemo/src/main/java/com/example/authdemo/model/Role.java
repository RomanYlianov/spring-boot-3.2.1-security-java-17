package com.example.authdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns
                    = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns
                    = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
