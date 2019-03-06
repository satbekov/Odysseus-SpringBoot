package kairat.dev.SP.Odysseus.Repositories;

import kairat.dev.SP.Odysseus.Models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByAuthorId(long id);
}
