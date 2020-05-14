package com.example.appmobilefoot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_club,button_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_club = findViewById(R.id.club_button);
        button_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clubIntent = new Intent(getApplicationContext(),ClubActivity.class);
                startActivity(clubIntent);
                finish();
            }
        });

        button_player = findViewById(R.id.player_button);
        button_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerIntent = new Intent(getApplicationContext(),PlayerActivity.class);
                startActivity(playerIntent);
                finish();
            }
        });
    }
}
