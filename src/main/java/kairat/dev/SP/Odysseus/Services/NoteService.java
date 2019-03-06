package kairat.dev.SP.Odysseus.Services;

import kairat.dev.SP.Odysseus.Models.Note;
import kairat.dev.SP.Odysseus.Models.User;
import kairat.dev.SP.Odysseus.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public void insert(Note note) {
        noteRepository.save(note);
    }

    public List<Note> findByUser(User user) {
        return noteRepository.findByAuthorId(user.getId());
    }
}
