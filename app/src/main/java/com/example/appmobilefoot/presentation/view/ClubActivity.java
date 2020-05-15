package com.example.appmobilefoot.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appmobilefoot.data.ClubAPI;
import com.example.appmobilefoot.R;
import com.example.appmobilefoot.presentation.model.Club;
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

public class ClubActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListClubAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private final String BASE_URL = "https://raw.githubusercontent.com/Eliott62/AppMobileFoot/master/app/src/main/java/com/example/appmobilefoot/";


    private SharedPreferences s;
    private Gson gson;
    private List<Club> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        gson = new GsonBuilder().setLenient().create();
        s = getSharedPreferences("Club", Context.MODE_PRIVATE);


        List<Club> liste = cache();
        if(liste != null){
            showList(liste);
        }else{
            makeAPICall();
        }
    }

    private void showList(List<Club> liste) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListClubAdapter(liste,getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }


    private void makeAPICall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ClubAPI clubApi = retrofit.create(ClubAPI.class);

        Call<List<Club>> call = clubApi.getClub();

      call.enqueue(new Callback<List<Club>>() {
          @Override
          public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
              list = response.body();
              saveList(list);
              showList(list);
              Toast.makeText(getApplicationContext(), "API success", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onFailure(Call<List<Club>> call, Throwable t) {
                showError();
          }
      });



    }

    private void saveList(List<Club> list){
        String jsonString  = gson.toJson(list);
        s.edit().putString("jsonString",jsonString).apply();
    }

    private List<Club> cache(){

        String jsonClub = s.getString("jsonString",null);
        if(jsonClub == null){
            return null;
        }else{
            Type listeType = new TypeToken<List<Club>>(){}.getType();
            return gson.fromJson(jsonClub,listeType);
        }
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

}
