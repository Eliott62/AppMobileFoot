package com.example.appmobilefoot.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.appmobilefoot.Constants;
import com.example.appmobilefoot.Singletons;
import com.example.appmobilefoot.data.PlayerAPI;
import com.example.appmobilefoot.presentation.model.Player;
import com.example.appmobilefoot.presentation.view.PlayerActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerController {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private PlayerActivity view;

    public PlayerController(PlayerActivity playerActivity,Gson gson, SharedPreferences sharedPreferences) {
        this.view = playerActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<Player> listPlayer = cache();
        if(listPlayer != null){
            view.showList(listPlayer);
        }else{
            makeAPICall();
        }
    }

    private void makeAPICall(){
        Call<List<Player>> call = Singletons.getPlayerApi().getPlayer();
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Player> playerList = response.body();
                    saveList(playerList);
                    view.showList(playerList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Player> list){
        String jsonString  = gson.toJson(list);
        sharedPreferences.edit().putString("jsonString",jsonString).apply();
    }

    private List<Player> cache(){
        String jsonPlayer = sharedPreferences.getString("jsonString",null);
        if(jsonPlayer == null){
            return null;
        }else{
            Type listeType = new TypeToken<List<Player>>(){}.getType();
            return gson.fromJson(jsonPlayer,listeType);
        }
    }

    public void onItemClick(Player player){
    }
}
