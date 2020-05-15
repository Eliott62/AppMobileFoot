package com.example.appmobilefoot.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobilefoot.R;
import com.example.appmobilefoot.presentation.controller.ClubController;
import com.example.appmobilefoot.presentation.model.Club;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ClubActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListClubAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ClubController clubController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        clubController = new ClubController(
                this,
                new GsonBuilder()
                .setLenient()
                .create(),
                getSharedPreferences("Club", Context.MODE_PRIVATE)

        );
        clubController.onStart();
    }

    public void showList(List<Club> liste) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListClubAdapter(liste,getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

}
