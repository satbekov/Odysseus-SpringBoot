package kairat.dev.SP.Odysseus.Controllers;

import kairat.dev.SP.Odysseus.Models.Note;
import kairat.dev.SP.Odysseus.Services.NoteService;
import kairat.dev.SP.Odysseus.Services.UserService;
import kairat.dev.SP.Odysseus.configs.MySSUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/notes")
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping(value = "/notes/{username}")
    public List<Note> noteByUsername(@PathVariable String username) {
        return noteService.findByUser(userService.getUser(username));
    }

    @PostMapping(value = "/note")
    public String publishNote(@RequestBody Note note) {
        MySSUserDetails userDetails = (MySSUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(note.getNoteCreationDate() == null) {
            note.setNoteCreationDate(new Date());
        }
        note.setAuthor(userService.getUser(userDetails.getUsername()));
        noteService.insert(note);
        return "Note was published";
    }
}
