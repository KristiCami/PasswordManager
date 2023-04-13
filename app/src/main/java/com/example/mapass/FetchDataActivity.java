package com.example.mapass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;
import android.os.Bundle;
import android.widget.Toast;
import com.example.mapass.databinding.ActivityFetchDataActivityBinding;
import java.util.List;
public class FetchDataActivity extends AppCompatActivity {
    ActivityFetchDataActivityBinding binding;
    MyDatabase myDb;
    MyInterface myInt;
    List<Accounts> accountsList;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFetchDataActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb = Room.databaseBuilder(this,MyDatabase.class,"accounts").allowMainThreadQueries().build();
        myInt=myDb.myInterface();

        accountsList=myInt.getAllAccounts();
        Toast.makeText(this,"You have added "+accountsList.size() + " accounts",Toast.LENGTH_SHORT ).show();
        myAdapter=new MyAdapter(this,accountsList);
        binding.dataRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.dataRecycler.setAdapter(myAdapter);

    }
}