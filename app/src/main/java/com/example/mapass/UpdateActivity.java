package com.example.mapass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mapass.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;
    MyDatabase myDb;
    MyInterface myInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb= Room.databaseBuilder(this,MyDatabase.class,"accounts").allowMainThreadQueries().build();
        myInt=myDb.myInterface();

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        int id = getIntent().getIntExtra("id",-1);

        binding.socialMediaName.setText(name);
        binding.email.setText(email);
        binding.password.setText(password);

        binding.updateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.socialMediaName.getText().toString();
                String email = binding.email.getText().toString();
                String pass = binding.password.getText().toString();

                if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name, email, and password are required", Toast.LENGTH_SHORT).show();
                }
                else {

                    Accounts accounts = new Accounts(id, name, email, pass);
                    myInt.update(accounts);
                    Toast.makeText(getApplicationContext(), "Account updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        binding.delAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInt.deleteAcc(id);
                Toast.makeText(getApplicationContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FetchDataActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}