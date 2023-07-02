package com.example.mvvmtodolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.mvvmtodolist.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        notesViewModel=new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NotesViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
                intent.putExtra("type","add mode");
                startActivityForResult(intent,1);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        NoteAdapter adapter=new NoteAdapter();
        binding.recyclerView.setAdapter(adapter);
        notesViewModel.GetAll().observe(this, new Observer<List<NotesModel>>() {
            @Override
            public void onChanged(List<NotesModel> notesModels) {
                adapter.submitList(notesModels);
            }
        });


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.LEFT) {
                    Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
                    intent.putExtra("title",adapter.GetNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("description",adapter.GetNote(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("type","update");
                    intent.putExtra("id",adapter.GetNote(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);
                }
                else {
                    notesViewModel.Delete(adapter.GetNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        };

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        touchHelper.attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            assert data != null;
            String title=data.getStringExtra("title");
            String description=data.getStringExtra("description");
            NotesModel notesModel=new NotesModel(title,description);
            notesViewModel.Insert(notesModel);
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==2){
            assert data != null;
            String title=data.getStringExtra("title");
            String description=data.getStringExtra("description");
            NotesModel notesModel=new NotesModel(title,description);
            notesModel.setId(data.getIntExtra("id",0));
            notesViewModel.Update(notesModel);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}