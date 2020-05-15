package com.example.appmobilefoot;

import com.example.appmobilefoot.data.ClubAPI;
import com.example.appmobilefoot.data.PlayerAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static ClubAPI clubApiInstance;
    private static PlayerAPI playerApiInstance;

    public static Gson getGson() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static ClubAPI getClubApi(){
        if(clubApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            clubApiInstance = retrofit.create(ClubAPI.class);
        }
        return clubApiInstance;
    }

    public static PlayerAPI getPlayerApi(){
        if(playerApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            playerApiInstance = retrofit.create(PlayerAPI.class);
        }
        return playerApiInstance;
    }
}
