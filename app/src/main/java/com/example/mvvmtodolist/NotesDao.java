package com.example.mvvmtodolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    public void Insert(NotesModel notes);

    @Update
    public void Update(NotesModel notes);

    @Delete
    public void Delete(NotesModel notes);

    @Query("SELECT *FROM myNotes")
    public LiveData<List<NotesModel>> GetAll();
}
