package com.example.mvvmtodolist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepositoryClass {
    private final NotesDao notesDao;
    private final LiveData<List<NotesModel>> notesList;

    public NotesRepositoryClass(Application application) {
        NotesDatabase notesDatabase=NotesDatabase.getInstance(application);
        notesDao=notesDatabase.notesDao();
        notesList=notesDao.GetAll();
    }

    public void InsertData(NotesModel notes){new InsertTask(notesDao).execute(notes);}

    public void UpdateData(NotesModel notes){new UpdateTask(notesDao).execute(notes);}

    public void DeleteData(NotesModel notes){new DeleteTask(notesDao).execute(notes);}

    public LiveData<List<NotesModel>> GetAllData(){
        return notesList;
    }

    private static class InsertTask extends AsyncTask<NotesModel,Void,Void>{
        private final NotesDao notesDao;

        @SuppressWarnings("deprecation")
        public InsertTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            notesDao.Insert(notesModels[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<NotesModel,Void,Void>{
        private final NotesDao notesDao;

        @SuppressWarnings("deprecation")
        public UpdateTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            notesDao.Update(notesModels[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<NotesModel,Void,Void>{
        private final NotesDao notesDao;

        @SuppressWarnings("deprecation")
        public DeleteTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            notesDao.Delete(notesModels[0]);
            return null;
        }
    }
}
