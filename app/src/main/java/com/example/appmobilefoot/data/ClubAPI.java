package com.example.appmobilefoot.data;



import com.example.appmobilefoot.presentation.model.Club;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClubAPI {

    @GET("api_club.json")
    Call<List<Club>> getClub  ();
}