package com.example.appmobilefoot.data;

import com.example.appmobilefoot.presentation.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlayerAPI {
    @GET("api_player.json")
    Call<List<Player>> getPlayer  ();
}
