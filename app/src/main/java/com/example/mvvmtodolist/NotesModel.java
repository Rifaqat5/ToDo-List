package com.example.mvvmtodolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myNotes")
public class NotesModel {
    private String title,description;



    @PrimaryKey(autoGenerate = true)
    private int id;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public NotesModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
