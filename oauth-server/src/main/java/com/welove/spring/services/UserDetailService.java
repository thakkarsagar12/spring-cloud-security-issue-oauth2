package com.welove.spring.services;

import com.welove.spring.model.Users;
import com.welove.spring.model.UsersDto;
import com.welove.spring.repository.RoleRepository;
import com.welove.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final RoleRepository authorityRepository;

    @Autowired
    public UserDetailService(UsersRepository usersRepository, RoleRepository authorityRepository) {
        this.usersRepository = usersRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return usersRepository.findByUsername(s).get();
    }

    //    @Override
    public Users save(UsersDto user) {
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
//        newUser.setAge(user.getAge());
//        newUser.setSalary(user.getSalary());
//        List<Authority> roles = authorityRepository.findAll(user.getAuthorities());
        newUser.setAuthorities(user.getAuthorities());
//        newUser.setEmail(user.getEmail());
        return usersRepository.save(newUser);
    }
}
