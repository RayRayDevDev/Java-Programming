package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "toDoBasic", schema = "dbo", catalog = "hp")
public class ToDoBasicEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Note")
    private String note;

    public int getNoteId() {
        return id;
    }

    public void setNoteId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoBasicEntity that = (ToDoBasicEntity) o;

        if (id != that.id) return false;
        return Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
