package com.example.appmobilefoot.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.appmobilefoot.Constants;
import com.example.appmobilefoot.Singletons;
import com.example.appmobilefoot.data.ClubAPI;
import com.example.appmobilefoot.presentation.model.Club;
import com.example.appmobilefoot.presentation.view.ClubActivity;
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

public class ClubController {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private ClubActivity view;

    public ClubController(ClubActivity clubActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = clubActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {
        List<Club> listClub = cache();
        if (listClub != null) {
            view.showList(listClub);
        } else {
            makeAPICall();
        }
    }

    private void makeAPICall() {
        Call<List<Club>> call = Singletons.getClubApi().getClub();
        call.enqueue(new Callback<List<Club>>() {
            @Override
            public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Club> clubList = response.body();
                    saveList(clubList);
                    view.showList(clubList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<Club>> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Club> list) {
        String jsonString = gson.toJson(list);
        sharedPreferences.edit().putString("jsonString", jsonString).apply();
    }

    private List<Club> cache() {
        String jsonClub = sharedPreferences.getString("jsonString", null);
        if (jsonClub == null) {
            return null;
        } else {
            Type listeType = new TypeToken<List<Club>>() {
            }.getType();
            return gson.fromJson(jsonClub, listeType);
        }
    }

    public void onItemClick(Club club) {
        view.navigateToDetails(club);
    }
}

