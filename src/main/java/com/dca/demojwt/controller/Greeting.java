package com.dca.demojwt.controller;

import com.dca.demojwt.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greeting")
public class Greeting {

    @GetMapping("/")
    public ResponseEntity<Message> greet() {
        Message message = new Message(2, "Hello Coders", "OK");
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}
