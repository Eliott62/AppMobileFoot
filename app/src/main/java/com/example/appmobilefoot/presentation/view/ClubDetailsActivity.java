package com.example.appmobilefoot.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appmobilefoot.R;
import com.example.appmobilefoot.Singletons;
import com.example.appmobilefoot.presentation.model.Club;
import com.example.appmobilefoot.presentation.model.Player;

public class ClubDetailsActivity extends AppCompatActivity {

    private TextView clubDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);

        clubDetail = findViewById(R.id.detail_club_text);
        Intent clubIntent = getIntent();
        String clubJson = clubIntent.getStringExtra("clubKey");
        Club club = Singletons.getGson().fromJson(clubJson, Club.class);
    
        showDetail(club);
    }

    private void showDetail(Club club) {
        clubDetail.setText(club.getName());
    }
}
