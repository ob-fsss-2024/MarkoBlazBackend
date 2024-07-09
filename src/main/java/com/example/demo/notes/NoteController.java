package com.example.demo.notes;


import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/note/{id}")
    public String getNote(@PathVariable Long id) {
        return noteRepository.findById(id).get().getText();

    }

    @PostMapping("/note")
    public NoteEntity createNote(@RequestBody CreateNote createNote){
        NoteEntity note = new NoteEntity();
        note.setTitle(createNote.title());
        note.setText(createNote.text());

        return noteRepository.save(note);
    }
}

record CreateNote(String title, String text) {}