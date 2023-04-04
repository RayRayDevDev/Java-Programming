package dev.rayraydev.formythirdtricktodolist;

import jakarta.persistence.*;

@Entity
@Table(name = "toDoBasic")
public
class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String Note;

    public int getNoteId() {
        return id;
    }

    public void setNoteId(int id) {
        this.id = id;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String task) {
        this.Note = task;
    }
}
