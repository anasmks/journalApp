package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    //this one is using in createEntry
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try{
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            //getting saved entry from the database
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            //now adding this saved entry into user's journal entries
            user.getJournalEntries().add(saved);
            //saving the user with updated entries
            userService.saveUser(user);
        }
        catch (Exception e){
            log.error("Error", e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    //this one is using in updateJournalEntryById
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try {
            // finding user in the database by username
            User user = userService.findByUserName(username);
            // removing all journal entries
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            // after removing -> updating user
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            log.error("Error", e);
            throw new RuntimeException("An error occurred while deleting the entry: "+e);
        }
        return removed;
    }

}

//controller  ---> service  ---> repository
