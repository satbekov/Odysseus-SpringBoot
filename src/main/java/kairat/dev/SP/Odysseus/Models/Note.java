package kairat.dev.SP.Odysseus.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String noteTitle;
    private String noteBody;
    private Date noteCreationDate;

    @ManyToOne
    private User author;

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public Date getNoteCreationDate() {
        return noteCreationDate;
    }

    public void setNoteCreationDate(Date noteCreationDate) {
        this.noteCreationDate = noteCreationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
