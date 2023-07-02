package com.example.mvvmtodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmtodolist.databinding.ActivityDataInsertBinding;

import java.util.Objects;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        String type=getIntent().getStringExtra("type");
        if(type.equals("update")){
            setTitle("update");
            binding.title1.setText(getIntent().getStringExtra("title"));
            binding.description1.setText(getIntent().getStringExtra("description"));
            int id=getIntent().getIntExtra("id",0);
            binding.add.setText("Update");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title1.getText().toString());
                    intent.putExtra("description", binding.description1.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
        else {
            setTitle("add mode");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title1.getText().toString());
                    intent.putExtra("description", binding.description1.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}