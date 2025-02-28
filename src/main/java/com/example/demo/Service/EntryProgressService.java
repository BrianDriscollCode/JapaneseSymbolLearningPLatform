package com.example.demo.Service;

import com.example.demo.Dao.EntryProgressRepository;
import com.example.demo.Entity.EntryProgress;
import com.example.demo.Entity.User;
import com.example.demo.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntryProgressService {
    @Autowired
    EntryProgressRepository entryProgressRepository;

    //@Autowired


    public void insertEntries(List<Entry> entries, User user)
    {
        System.out.println(user.getName() + " : " + user.getId());
        List<EntryProgress> dbEntries;

        try
        {
            dbEntries = entryProgressRepository.findByUserId(user);
        }
        catch(Exception error)
        {
            System.out.println(error + ": No entries found");
        }

        for (Entry entry: entries)
        {
            System.out.println("entry romaji:" + entry.getRomaji());
            System.out.println("entry type:" + entry.getType());
            System.out.println("entry res:" + entry.getResponse());
        }
    }
}
