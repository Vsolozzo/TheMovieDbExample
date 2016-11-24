package com.example.furkanbozkurt.themoviedbexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.furkanbozkurt.themoviedbexample.R;
import com.example.furkanbozkurt.themoviedbexample.model.Movie;

import java.util.List;

/**
 * Created by Furkan Bozkurt on 24.11.2016.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public MovieViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view.findViewById(R.id.movies_layout);
            movieTitle = (TextView) view.findViewById(R.id.title);
            data = (TextView) view.findViewById(R.id.subtitle);
            movieDescription = (TextView) view.findViewById(R.id.description);
            rating = (TextView) view.findViewById(R.id.rating);
        }

    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {

        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



}
