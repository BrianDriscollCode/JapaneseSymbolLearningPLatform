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

    // ******************************
    // * Function: insertEntries
    // * Purpose: Inserts or updates user entry progress based on submitted entries.
    // * Calls: entryProgressRepository.findByUser, entryProgressRepository.save
    // *
    // * This function:
    // * - Retrieves the user's existing progress from the database.
    // * - Maps incoming entries by their romaji + type for quick lookup.
    // * - Updates existing database entries by adjusting their score.
    // * - Inserts new entries if they don't already exist.
    // *
    // * @param entries - List<Entry> - New entries submitted by the user.
    // * @param user - User - The user submitting the entries.
    // ******************************

    public void insertEntries(List<Entry> entries, User user)
    {
        List<EntryProgress> dbEntries = new ArrayList<>();

        try
        {
            // Retrieve the user's current progress from the database
            dbEntries = entryProgressRepository.findByUser(user);
        }
        catch(Exception error)
        {
            System.out.println(error + ": No entries found");
        }

        // ******************************
        // * Step 1: Create a lookup map for the new entries
        // * This allows quick comparison of incoming entries with existing database records.
        // ******************************

        HashMap<String, List<Entry>> compareMap = new HashMap<>();
        for (Entry entry : entries) {
            // Generate a unique key using romaji and entry type (kanaType).
            String key = entry.getRomaji() + "_" + entry.getType();  // Combine romaji + kanaType
            compareMap.putIfAbsent(key, new ArrayList<>());
            compareMap.get(key).add(entry);
        }

        // ******************************
        // * Step 2: Update existing database entries if they match submitted entries.
        // ******************************

        // **** FUTURE REFACTOR - Collect entries and use saveAll to batch updates
        if (!dbEntries.isEmpty())
        {
            System.out.println("DB_ENTRIES is not empty");

            // If a matching entry exists in the submitted data
            for (EntryProgress dbEntry : dbEntries) {
                long points = 0;
                String key = dbEntry.getRomaji() + "_" + dbEntry.getKanaType(); // Match kanaType too

                // Calculate the score adjustment based on user response
                if (compareMap.containsKey(key)) {
                    for (Entry entry : compareMap.get(key)) {
                        points += entry.getResponse() ? 1 : -1;
                    }

                    // Remove from compareMap as it has been processed
                    compareMap.remove(key);

                    // Update the score of the existing entry
                    dbEntry.setScore(dbEntry.getScore() + points);

                    // Save the updated entry back to the database
                    entryProgressRepository.save(dbEntry);
                }
            }
        }

        // ******************************
        // * Step 3: Insert new entries that are not found in the database
        // ******************************

        List<Entry> newEntries = new ArrayList<>();

        // Gather remaining entries from compareMap (entries that didn't match an existing record)
        for (List<Entry> entryList : compareMap.values())
        {
            newEntries.addAll(entryList);
        }

        // Process new entries and insert them into the database
        for (Entry entry : newEntries) {
            String key = entry.getRomaji() + "_" + entry.getType();

            // Check if the entry already exists in the database
            Optional<EntryProgress> existingProgress =
                    entryProgressRepository.findByUserAndRomajiAndKanaType(user, entry.getRomaji(), entry.getType());

            EntryProgress progressDataObject;

            if (existingProgress.isPresent()) {
                // If an entry exists, update its score based on user response
                long points = entry.getResponse() ? 1 : -1;
                progressDataObject = existingProgress.get();
                progressDataObject.setScore(progressDataObject.getScore() + points);
            } else {
                // Create a new EntryProgress object if it doesn't exist
                progressDataObject = new EntryProgress();
                progressDataObject.setScore(1L);
            }

            // ******************************
            // * Step 4: Set object properties and save to the database
            // ******************************
            progressDataObject.setUser(user);
            progressDataObject.setRomaji(entry.getRomaji());
            progressDataObject.setKanaType(entry.getType());
            progressDataObject.setRow(entry.getRow());

            entryProgressRepository.save(progressDataObject);
        }
    }
}
