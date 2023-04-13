package com.example.mapass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mapass.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyDatabase myDb;
    MyInterface myInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb= Room.databaseBuilder(this,MyDatabase.class,"accounts").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        myInt=myDb.myInterface();

        binding.addAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.socialMediaName.getText().toString();
                String email = binding.mobileNumber.getText().toString();
                String password = binding.password.getText().toString();

                Accounts accounts = new Accounts(0,name,email,password);
                myInt.insert(accounts);
                Toast.makeText(getApplicationContext(), "Account added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        binding.showAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FetchDataActivity.class));
            }
        });

    }
}