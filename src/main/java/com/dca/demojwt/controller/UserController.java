package com.dca.demojwt.controller;

import com.dca.demojwt.model.User;
import com.dca.demojwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List listUser(){ return userServiceImpl.findAll(); }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userServiceImpl.save(user);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Optional<User> findOne(@PathVariable String id){
        return userServiceImpl.findById(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable String id, @RequestBody User user){
        user.setId(id);
        return userServiceImpl.save(user);
    }
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") String id){
        userServiceImpl.deleteById(id);
    }

}
