package com.example.appmobilefoot.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appmobilefoot.R;
import com.example.appmobilefoot.Singletons;
import com.example.appmobilefoot.presentation.model.Player;

public class PlayerDetailsActivity extends AppCompatActivity {

    private TextView playerName,playerClub,playerPosition,playerNationality;
    private ImageView playerPhoto;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        playerName = findViewById(R.id.player_name);
        playerPhoto = findViewById(R.id.player_photo);
        playerClub = findViewById(R.id.player_club);
        playerPosition = findViewById(R.id.player_position);
        playerNationality = findViewById(R.id.player_nationality);

        Intent playerIntent = getIntent();

        String playerJson = playerIntent.getStringExtra("playerNameKey");
        Player player = Singletons.getGson().fromJson(playerJson, Player.class);

        showDetail(player);
    }

    @SuppressLint("SetTextI18n")
    private void showDetail(Player player) {
        playerName.setText(player.getSurname() + " " +player.getName());
        Glide.with(this).load(player.getImageURL()).into(playerPhoto);
        playerClub.setText("Club du joueur : " + player.getClub());
        playerPosition.setText("Position du joueur : " + player.getPosition());
        playerNationality.setText("Pays du joueur : " + player.getNationality());
    }
}
