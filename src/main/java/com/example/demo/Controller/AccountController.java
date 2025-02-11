package com.example.demo.Controller;


import com.example.demo.Dto.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getUser/{uuid}")
    public User getUser(@PathVariable UUID uuid)
    {
        return accountService.getUser(uuid);
    }

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
