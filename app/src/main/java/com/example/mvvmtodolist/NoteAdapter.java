package com.example.mvvmtodolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtodolist.databinding.NotesSampleBinding;

import java.util.zip.Inflater;

public class NoteAdapter extends ListAdapter<NotesModel,NoteAdapter.ViewHolder> {

    public NoteAdapter(){
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<NotesModel> CALLBACK=new DiffUtil.ItemCallback<NotesModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull NotesModel oldItem, @NonNull NotesModel newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NotesModel oldItem, @NonNull NotesModel newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && newItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesModel note=getItem(position);
        holder.binding.title.setText(note.getTitle());
        holder.binding.description.setText(note.getDescription());
    }
    public NotesModel GetNote(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        NotesSampleBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=NotesSampleBinding.bind(itemView);
        }
    }
}
