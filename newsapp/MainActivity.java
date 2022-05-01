package com.wowvio.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    adpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final api apiService = RetrofitClient.getClient().create(api.class);

        Call<model> call = apiService.getpost("techcrunch", "d51fdfc3bce14675926d2aef6b126c53");

        call.enqueue(new Callback<model>() {
            @Override
            public void onResponse(@NonNull Call<model> call, @NonNull Response<model> response) {

                //In this point we got our hero list
                //thats damn easy right ;)
                //List<model> heroList = response.body();
                if (response.body().getStatus().equals("ok")) {

                    List<article> articleList = response.body().getArticles();
                    //now we can do whatever we want with this list
                    if (articleList.size() > 0) {

                        adapter = new adpater(articleList);
                        recyclerView.setAdapter(adapter);

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<model> call, @NonNull Throwable t) {
                //handle error or failure cases here
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

}
