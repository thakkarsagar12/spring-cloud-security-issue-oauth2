package com.welove.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private Long id;

    private String username;

    private String password;


    private List<Role> authorities;

    private boolean enable;


    public boolean isAccountNonExpired() {
        return enable;
    }

    public boolean isAccountNonLocked() {
        return enable;
    }

    public boolean isCredentialsNonExpired() {
        return enable;
    }

    public boolean isEnabled() {
        return enable;
    }
}
