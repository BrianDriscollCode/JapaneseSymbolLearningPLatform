package com.example.demo.Controller;


import com.example.demo.Dto.UserDTO;
import com.example.demo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO)
    {
        System.out.println("TEST");
        UUID uuid = userDTO.getUuid();
        String name = userDTO.getName();
        String email = userDTO.getEmail();
        accountService.createUser(uuid, name, email);

        return ResponseEntity.ok("Success");
    }
}
