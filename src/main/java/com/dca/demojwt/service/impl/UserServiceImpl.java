package com.dca.demojwt.service.impl;

import com.dca.demojwt.repository.UserRepository;
import com.dca.demojwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.dca.demojwt.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserServiceImpl --> loadUserByUsername");
        User user = userRepository.findByUsername(username);
        System.out.println(user.toString());
        if(user == null){
            System.out.println("Invalid username or password.");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        System.out.println("UserServiceImpl --> getAuthority");
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public List findAll(){
        return userRepository.findAll();
    }

    @Override
    public User save(@RequestBody User user){
        System.out.println("UserServiceImpl user: " + user.toString());
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(@PathVariable String id){
        return userRepository.findById(id);
    }

    @Override
    public User update(@PathVariable String id, @RequestBody User user){
        user.setId(id);
        return userRepository.save(user);
    }
    @Override
    public void deleteById(@PathVariable(value = "id") String id){
        userRepository.deleteById(id);
    }

}
