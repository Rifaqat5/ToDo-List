package com.example.mvvmtodolist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = NotesModel.class,version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase instance;
    public abstract NotesDao notesDao();
    public static synchronized NotesDatabase getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,"NotesDatabase")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
