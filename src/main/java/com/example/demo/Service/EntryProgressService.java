package com.example.demo.Service;

import com.example.demo.Dao.EntryProgressRepository;
import com.example.demo.Dao.UsersRepository;
import com.example.demo.Entity.EntryProgress;
import com.example.demo.Entity.User;
import com.example.demo.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EntryProgressService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    EntryProgressRepository entryProgressRepository;

    public List<EntryProgress> getEntries(UUID uuid)
    {
        User user = usersRepository.findByUuid(uuid);
        return entryProgressRepository.findByUser(user);
    }

    public void insertEntries(List<Entry> entries, User user)
    {
        List<EntryProgress> dbEntries = new ArrayList<>();

        try
        {
            // Get current entries in database
            dbEntries = entryProgressRepository.findByUser(user);
        }
        catch(Exception error)
        {
            System.out.println(error + ": No entries found");
        }

        // Map the romaji values for easier lookup
//        HashMap<String, List<Entry>> compareMap = new HashMap<>();
//        for (Entry entry : entries)
//        {
//            String romaji = entry.getRomaji();
//            compareMap.putIfAbsent(romaji, new ArrayList<>());
//            compareMap.get(romaji).add(entry);
//        }
        HashMap<String, List<Entry>> compareMap = new HashMap<>();
        for (Entry entry : entries) {
            String key = entry.getRomaji() + "_" + entry.getType();  // Combine romaji + kanaType
            compareMap.putIfAbsent(key, new ArrayList<>());
            compareMap.get(key).add(entry);
        }

        // Run if there are no entries
        if (!dbEntries.isEmpty())
        {
            System.out.println("DB_ENTRIES is not empty");

            // Iterate through EXISTING DB entries to update them
//            for (EntryProgress dbEntry : dbEntries)
//            {
//                long points = 0;
//                String romaji = dbEntry.getRomaji();
//
//                if (compareMap.containsKey(romaji))
//                {
//                    // Process the associated entries in compareMap
//                    for (Entry entry : compareMap.get(romaji))
//                    {
//                        System.out.println(entry.getRomaji() + ": " + entry.getResponse());
//                        if (entry.getResponse())
//                        {
//                            points += 1;
//                        }
//                        else
//                        {
//                            points -= 1;
//                        }
//                    }
//
//                    // Remove the processed romaji from compareMap
//                    compareMap.remove(romaji);
//
//                    // Update the score in the DB
//                    dbEntry.setScore(dbEntry.getScore() + points);
//                    entryProgressRepository.save(dbEntry);
//                }
//            }
            for (EntryProgress dbEntry : dbEntries) {
                long points = 0;
                String key = dbEntry.getRomaji() + "_" + dbEntry.getKanaType(); // Match kanaType too

                if (compareMap.containsKey(key)) {
                    for (Entry entry : compareMap.get(key)) {
                        System.out.println(entry.getRomaji() + ": " + entry.getResponse());
                        points += entry.getResponse() ? 1 : -1;
                    }

                    compareMap.remove(key);  // Remove processed key
                    dbEntry.setScore(dbEntry.getScore() + points);
                    entryProgressRepository.save(dbEntry);
                }
            }
        }

        // Insert new entries for romaji not found in the database
        List<Entry> newEntries = new ArrayList<>();
        for (List<Entry> entryList : compareMap.values())
        {
            newEntries.addAll(entryList);
        }

        // Loop through left over new entries (not existing in DB)
//        for (Entry entry : newEntries)
//        {
//            // Check if the EntryProgress already exists for this romaji (happens if quiz generates duplicate romaji)
//            // *duplicate romaji is intended if a romaji has a question asked multiple times, this just handles that case
//            Optional<EntryProgress> existingProgress = entryProgressRepository.findByUserAndRomaji(user, entry.getRomaji());
//
//            EntryProgress progressDataObject;
//
//            // Calculate points based on current score
//            if (existingProgress.isPresent())
//            {
//                long points = 0;
//                progressDataObject = existingProgress.get(); // Unwrap the Optional
//
//                if (entry.getResponse())
//                {
//                    points += 1;
//                }
//                else
//                {
//                    points -= 1;
//                }
//                progressDataObject.setScore(progressDataObject.getScore() + points);
//            }
//            // If not duplicated, treat as new entry and set score
//            else
//            {
//                progressDataObject = new EntryProgress();
//                progressDataObject.setScore(1L); // Set appropriate score
//            }
//
//            //set object variables before saving to DB
//            System.out.println(entry.getRomaji() + ": " + entry.getResponse());
//            progressDataObject.setUser(user);
//            progressDataObject.setRomaji(entry.getRomaji());
//            progressDataObject.setKanaType(entry.getType());
//            progressDataObject.setRow(entry.getRow());
//
//            entryProgressRepository.save(progressDataObject);
//        }
        for (Entry entry : newEntries) {
            String key = entry.getRomaji() + "_" + entry.getType();

            Optional<EntryProgress> existingProgress =
                    entryProgressRepository.findByUserAndRomajiAndKanaType(user, entry.getRomaji(), entry.getType());

            EntryProgress progressDataObject;

            if (existingProgress.isPresent()) {
                long points = entry.getResponse() ? 1 : -1;
                progressDataObject = existingProgress.get();
                progressDataObject.setScore(progressDataObject.getScore() + points);
            } else {
                progressDataObject = new EntryProgress();
                progressDataObject.setScore(1L);
            }

            // Set object variables before saving to DB
            progressDataObject.setUser(user);
            progressDataObject.setRomaji(entry.getRomaji());
            progressDataObject.setKanaType(entry.getType());
            progressDataObject.setRow(entry.getRow());

            entryProgressRepository.save(progressDataObject);
        }




    }


}
