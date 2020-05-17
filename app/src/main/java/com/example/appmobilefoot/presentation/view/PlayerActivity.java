package com.example.appmobilefoot.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobilefoot.R;
import com.example.appmobilefoot.Singletons;
import com.example.appmobilefoot.presentation.controller.PlayerController;
import com.example.appmobilefoot.presentation.model.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListPlayerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private PlayerController playerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerController = new PlayerController(
                this,
                new GsonBuilder()
                        .setLenient()
                        .create(),
                getSharedPreferences("Player", Context.MODE_PRIVATE)

        );
        playerController.onStart();
    }

    public void showList(List<Player> liste) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListPlayerAdapter(liste,getApplicationContext(), new ListPlayerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Player item) {
                playerController.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Player player) {
        Intent playerIntent = new Intent(PlayerActivity.this, PlayerDetailsActivity.class);

        playerIntent.putExtra("playerNameKey", Singletons.getGson().toJson(player));
        playerIntent.putExtra("playerPhotoKey", Singletons.getGson().toJson(player));

        PlayerActivity.this.startActivity(playerIntent);
    }
}

