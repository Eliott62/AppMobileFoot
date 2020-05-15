package com.example.appmobilefoot.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appmobilefoot.data.PlayerAPI;
import com.example.appmobilefoot.R;
import com.example.appmobilefoot.presentation.model.Player;
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

public class PlayerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListPlayerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private final String BASE_URL = "https://raw.githubusercontent.com/Eliott62/AppMobileFoot/master/app/src/main/java/com/example/appmobilefoot/";


    private SharedPreferences s;
    private Gson gson;
    private List<Player> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        gson = new GsonBuilder().setLenient().create();
        s = getSharedPreferences("Player", Context.MODE_PRIVATE);


        List<Player> liste = cache();
        if(liste != null){
            showList(liste);
        }else{
            makeAPICall();
        }
    }

    private void showList(List<Player> liste) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListPlayerAdapter(liste,getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }


    private void makeAPICall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PlayerAPI playerApi = retrofit.create(PlayerAPI.class);

        Call<List<Player>> call = playerApi.getPlayer();

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                list = response.body();
                saveList(list);
                showList(list);
                Toast.makeText(getApplicationContext(), "API success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                showError();
            }
        });



    }

    private void saveList(List<Player> list){
        String jsonString  = gson.toJson(list);
        s.edit().putString("jsonString",jsonString).apply();
    }

    private List<Player> cache(){

        String jsonPlayer = s.getString("jsonString",null);
        if(jsonPlayer == null){
            return null;
        }else{
            Type listeType = new TypeToken<List<Player>>(){}.getType();
            return gson.fromJson(jsonPlayer,listeType);
        }
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

}

