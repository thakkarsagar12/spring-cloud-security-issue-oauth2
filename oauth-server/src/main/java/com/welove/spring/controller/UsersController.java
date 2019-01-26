package com.welove.spring.controller;

import com.welove.spring.model.Users;
import com.welove.spring.model.UsersDto;
import com.welove.spring.repository.UsersRepository;
import com.welove.spring.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UsersController {

    @Autowired
    private UsersRepository userService;

    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<Users> listUsers(){
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Optional<Users> getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOne(@PathVariable(value = "id") Long id){
        System.out.println("Fetching & Deleting Users with id :" + id);

        userService.deleteById(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public Users saveUsers(@RequestBody UsersDto user){
        return userDetailService.save(user);
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal ) {

        String name = principal.getName(); //get logged in username
        model.addAttribute("username", name);
        return name;

    }



}
