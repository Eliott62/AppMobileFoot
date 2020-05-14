package com.example.appmobilefoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlayerAPI {
    @GET("api_player.json")
    Call<List<Player>> getPlayer  ();
}
