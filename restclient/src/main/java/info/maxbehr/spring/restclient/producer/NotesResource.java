package info.maxbehr.spring.restclient.producer;

import info.maxbehr.spring.restclient.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class NotesResource {

    private List<Note> notes = initNotes();

    @GetMapping("/notes")
    public List<Note> allNotes() {
        return notes;
    }

    @PostMapping("/notes")
    public ResponseEntity<Integer> createNote(@RequestBody Note note) {
        notes.add(note);
        return new ResponseEntity<>(notes.indexOf(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/notes/{id}")
    public Note deleteNote(@PathVariable int id) {
        return notes.remove(id);
    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable int id) {
        try {
            return notes.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new NoteNotFoundException(e);
        }
    }

    private List<Note> initNotes() {
        var noteList = new ArrayList<Note>();
        noteList.add(new Note(1, "first title", "first content"));
        noteList.add(new Note(2, "second title", "second content"));
        noteList.add(new Note(3, "third title", "third content"));
        noteList.add(new Note(4, "fourth title", "fourth content"));
        noteList.add(new Note(5, "fifth title", "fifth content"));
        noteList.add(new Note(6, "sixth title", "sixth content"));
        noteList.add(new Note(7, "seventh title", "seventh content"));
        noteList.add(new Note(8, "eigth title", "eigth content"));
        noteList.add(new Note(9, "ninth title", "ninth content"));
        noteList.add(new Note(10, "tenth title", "tenth content"));
        return noteList;
    }
}
