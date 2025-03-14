package com.example.demo.Controller;


import com.example.demo.Dto.EntryProgressRequestDTO;
import com.example.demo.Entity.EntryProgress;
import com.example.demo.Entity.User;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.EntryProgressService;
import com.example.demo.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/entryProgress")
public class EntryProgressController {

    @Autowired
    AccountService accountService;

    @Autowired
    EntryProgressService entryProgressService;

    @GetMapping("/get/{uuid}")
    public List<EntryProgress> getEntries(@PathVariable UUID uuid)
    {
        return entryProgressService.getEntries(uuid);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertEntries(@RequestBody EntryProgressRequestDTO entryRequest)
    {
        System.out.println("TEST TEST TEST INSERT");
        //System.out.println("Entries: " + entryRequest.getEntries());
        List<Entry> entries = entryRequest.getEntries();
        System.out.println(entryRequest.getUuid());
        User user = accountService.getUser(entryRequest.getUuid());
        //System.out.println(user)
        entryProgressService.insertEntries(entries, user);

        return ResponseEntity.ok("Success");
    }
}
