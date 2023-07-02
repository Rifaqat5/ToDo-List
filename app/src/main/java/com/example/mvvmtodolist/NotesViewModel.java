package com.example.mvvmtodolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class NotesViewModel extends AndroidViewModel {
    private NotesRepositoryClass notesRepositoryClass;
    private LiveData<List<NotesModel>> notesList;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepositoryClass=new NotesRepositoryClass(application);
        notesList=notesRepositoryClass.GetAllData();
    }

    public void Insert(NotesModel model){
        notesRepositoryClass.InsertData(model);
    }

    public void Update(NotesModel model){
        notesRepositoryClass.UpdateData(model);
    }

    public void Delete(NotesModel model){
        notesRepositoryClass.DeleteData(model);
    }

    public LiveData<List<NotesModel>> GetAll(){
        return notesList;
    }
}
