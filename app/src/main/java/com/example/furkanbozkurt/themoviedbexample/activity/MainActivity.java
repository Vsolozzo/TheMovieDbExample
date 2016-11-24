package com.example.furkanbozkurt.themoviedbexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.furkanbozkurt.themoviedbexample.R;
import com.example.furkanbozkurt.themoviedbexample.adapter.MoviesAdapter;
import com.example.furkanbozkurt.themoviedbexample.model.Movie;
import com.example.furkanbozkurt.themoviedbexample.model.MovieResponse;
import com.example.furkanbozkurt.themoviedbexample.rest.ApiClient;
import com.example.furkanbozkurt.themoviedbexample.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "10ad52a9e31212500752532dddaa9df5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Buraya dikkat et.

        ApiInterface servis = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = servis.getTopRatedMovies(API_KEY,"tr");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();
                List<Movie> movie = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movie, R.layout.list_item_movie, getApplicationContext()));
                Log.i("MainActiviy::onResponse", "Film sayısı : " + movie.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Log.i("MainActivity::onFailure", "Hata : " + t.toString());
            }
        });
    }
}
