package com.example.demo.Service;

import com.example.demo.Dao.UsersRepository;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    UsersRepository usersRepository;

    public User getUser(UUID uuid)
    {
        return usersRepository.findByUuid(uuid);
    }

    public void createUser(UUID uuid, String name, String email)
    {
        User user = new User();
        user.setUuid(uuid);
        user.setName(name);
        user.setEmail(email);

        usersRepository.save(user);
    }
}
