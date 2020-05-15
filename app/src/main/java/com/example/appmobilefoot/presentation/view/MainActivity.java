package com.example.appmobilefoot.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appmobilefoot.R;
import com.example.appmobilefoot.presentation.controller.MainController;

public class MainActivity extends AppCompatActivity {

    private Button button_club,button_player;

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainController = new MainController();
        mainController.onStart();

        button_club = findViewById(R.id.club_button);
        button_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clubIntent = new Intent(getApplicationContext(), ClubActivity.class);
                startActivity(clubIntent);
                finish();
            }
        });

        button_player = findViewById(R.id.player_button);
        button_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerIntent = new Intent(getApplicationContext(), PlayerActivity.class);
                startActivity(playerIntent);
                finish();
            }
        });
    }
}
