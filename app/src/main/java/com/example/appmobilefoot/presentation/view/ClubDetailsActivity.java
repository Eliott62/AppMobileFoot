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
import com.example.appmobilefoot.presentation.model.Club;
import com.example.appmobilefoot.presentation.model.Player;

public class ClubDetailsActivity extends AppCompatActivity {

    private TextView clubName,clubStadium,clubCoach,clubPresident,clubCreation;
    private ImageView clubLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);

        clubName = findViewById(R.id.club_name);
        clubLogo = findViewById(R.id.club_photo);
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
        Glide.with(this).load(club.getImageURL()).into(clubLogo);
        clubLogo.setImageResource(getIntent().getIntExtra("imageURL",0));
        clubStadium.setText("Nom du stade : " + club.getStadium());
        clubCoach.setText("Entraîneur : " + club.getCoach());
        clubPresident.setText("Président : " + club.getPresident());
        clubCreation.setText("Date de création : " + club.getCreation());
    }
}
