package com.example.appmobilefoot.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmobilefoot.R;
import com.example.appmobilefoot.Singletons;
import com.example.appmobilefoot.presentation.model.Club;
import com.example.appmobilefoot.presentation.model.Player;

public class ClubDetailsActivity extends AppCompatActivity {

    private TextView clubName,clubStadium,clubCoach,clubPresident,clubCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);

        clubName = findViewById(R.id.club_name);
        clubStadium = findViewById(R.id.stadium);
        clubCoach = findViewById(R.id.club_coach);
        clubPresident = findViewById(R.id.club_president);
        clubCreation = findViewById(R.id.club_creation);

        Intent clubIntent = getIntent();
        String clubJson = clubIntent.getStringExtra("clubKey");
        Club club = Singletons.getGson().fromJson(clubJson, Club.class);
    
        showDetail(club);
    }

    @SuppressLint("SetTextI18n")
    private void showDetail(Club club) {
        clubName.setText(club.getName());
        clubStadium.setText("Nom du stade : " + club.getStadium());
        clubCoach.setText("Entraîneur : " + club.getCoach());
        clubPresident.setText("Président : " + club.getPresident());
        clubCreation.setText("Date de création : " + club.getCreation());
    }
}
