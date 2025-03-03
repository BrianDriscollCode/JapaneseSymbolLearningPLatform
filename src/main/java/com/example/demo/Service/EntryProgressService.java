package com.example.demo.Service;

import com.example.demo.Dao.EntryProgressRepository;
import com.example.demo.Entity.EntryProgress;
import com.example.demo.Entity.User;
import com.example.demo.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EntryProgressService {
    @Autowired
    EntryProgressRepository entryProgressRepository;

    //@Autowired


//    public void insertEntries(List<Entry> entries, User user)
//    {
//        System.out.println(user.getName() + " : " + user.getUserID());
//        List<EntryProgress> dbEntries = new ArrayList<>();
//
//        try
//        {
//            dbEntries = entryProgressRepository.findByUser(user);
//        }
//        catch(Exception error)
//        {
//            System.out.println(error + ": No entries found");
//        }
//
//        if (dbEntries.size() > 0)
//        {
//            System.out.println("DB_ENTRIES is not empty");
//            HashMap<String, List<Entry>> compareMap = new HashMap<>();
//
//            for (Entry entry: entries)
//            {
//                String romaji = entry.getRomaji();
//                compareMap.putIfAbsent(romaji, new ArrayList<>());
//                compareMap.get(romaji).add(entry);
//            }
//            for (EntryProgress dbEntry: dbEntries)
//            {
//                long points = 0;
//                String romaji = dbEntry.getRomaji();
//
//                if (compareMap.containsKey(romaji))
//                {
//                    EntryProgress progressDataObject = dbEntry;
//
//                    for (Entry entry: compareMap.get(romaji))
//                    {
//                        if (entry.getResponse().equals("true"))
//                        {
//                            points += 1;
//                        }
//                        else
//                        {
//                            points -= 1;
//                        }
//                    }
//                    compareMap.remove(romaji);
//
//                    progressDataObject.setScore(progressDataObject.getScore() + points);
//                    entryProgressRepository.save(progressDataObject);
//                }
//            }
//
//            List<Entry> mergedList = new ArrayList<>();
//
//            for (List<Entry> entryList: compareMap.values())
//            {
//                mergedList.addAll(entryList);
//            }
//
//            for (Entry entry: mergedList)
//            {
//                EntryProgress progressDataObject = new EntryProgress();
//                progressDataObject.setUser(user);
//                progressDataObject.setRomaji(entry.getRomaji());
//                progressDataObject.setKanaType(entry.getType());
//                progressDataObject.setRow(entry.getRow());
//                progressDataObject.setScore(1L);
//
//                entryProgressRepository.save(progressDataObject);
//            }
//
//        }
//        else
//        {
//            System.out.println("Inserting entries");
//            for (Entry entry: entries)
//            {
//                EntryProgress progressDataObject = new EntryProgress();
//                progressDataObject.setUser(user);
//                progressDataObject.setRomaji(entry.getRomaji());
//                progressDataObject.setKanaType(entry.getType());
//                progressDataObject.setRow(entry.getRow());
//                progressDataObject.setScore(10L);
//
//                entryProgressRepository.save(progressDataObject);
//            }
//        }
//
//
////        for (Entry entry: entries)
////        {
////            System.out.println("entry romaji:" + entry.getRomaji());
////            System.out.println("entry type:" + entry.getType());
////            System.out.println("entry res:" + entry.getResponse());
////        }
//    }

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
        HashMap<String, List<Entry>> compareMap = new HashMap<>();
        for (Entry entry : entries)
        {
            String romaji = entry.getRomaji();
            compareMap.putIfAbsent(romaji, new ArrayList<>());
            compareMap.get(romaji).add(entry);
        }

        // Run is there are no entries
        if (!dbEntries.isEmpty())
        {
            System.out.println("DB_ENTRIES is not empty");

            // Iterate through EXISTING DB entries to update them
            for (EntryProgress dbEntry : dbEntries)
            {
                long points = 0;
                String romaji = dbEntry.getRomaji();

                if (compareMap.containsKey(romaji))
                {
                    // Process the associated entries in compareMap
                    for (Entry entry : compareMap.get(romaji))
                    {
                        System.out.println(entry.getRomaji() + ": " + entry.getResponse());
                        if (entry.getResponse())
                        {
                            points += 1;
                        }
                        else
                        {
                            points -= 1;
                        }
                    }

                    // Remove the processed romaji from compareMap
                    compareMap.remove(romaji);

                    // Update the score in the DB
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
        for (Entry entry : newEntries)
        {
            // Check if the EntryProgress already exists for this romaji (happens if quiz generates duplicate romaji)
            Optional<EntryProgress> existingProgress = entryProgressRepository.findByUserAndRomaji(user, entry.getRomaji());

            EntryProgress progressDataObject;

            // Calculate points based on current score
            if (existingProgress.isPresent())
            {
                long points = 0;
                progressDataObject = existingProgress.get(); // Unwrap the Optional

                if (entry.getResponse())
                {
                    points += 1;
                }
                else
                {
                    points -= 1;
                }
                progressDataObject.setScore(progressDataObject.getScore() + points);
            }
            // If not duplicated, treat as new entry and set score
            else
            {
                progressDataObject = new EntryProgress();
                progressDataObject.setScore(1L); // Set appropriate score
            }

            //set object variables before saving to DB
            System.out.println(entry.getRomaji() + ": " + entry.getResponse());
            progressDataObject.setUser(user);
            progressDataObject.setRomaji(entry.getRomaji());
            progressDataObject.setKanaType(entry.getType());
            progressDataObject.setRow(entry.getRow());

            entryProgressRepository.save(progressDataObject);
        }



    }


}
