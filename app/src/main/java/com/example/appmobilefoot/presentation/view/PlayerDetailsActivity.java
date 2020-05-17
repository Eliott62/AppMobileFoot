package com.example.appmobilefoot.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmobilefoot.R;
import com.example.appmobilefoot.Singletons;
import com.example.appmobilefoot.presentation.model.Player;

public class PlayerDetailsActivity extends AppCompatActivity {

    private TextView playerDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        playerDetail = findViewById(R.id.detail_player_text);
        Intent playerIntent = getIntent();
        String playerJson = playerIntent.getStringExtra("playerKey");
        Player player = Singletons.getGson().fromJson(playerJson, Player.class);

        showDetail(player);
    }

    @SuppressLint("SetTextI18n")
    private void showDetail(Player player) {
        playerDetail.setText(player.getSurname() + " " +player.getName());
    }
}
