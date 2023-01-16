package com.tc2r.dreammovieapp.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tc2r.dreammovieapp.databinding.MovieListItemBinding;
import com.tc2r.dreammovieapp.databinding.PopularItemListViewBinding;
import com.tc2r.dreammovieapp.models.MovieModel;
import com.tc2r.dreammovieapp.utils.Credentials;
import com.tc2r.dreammovieapp.utils.ListType;

import java.util.List;

/**
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/13/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> moviesList;
    private ListType listType = ListType.POPULAR;
    private final OnMovieListener onMovieListener;


    public MovieRecyclerAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == ListType.SEARCH.getViewType()) {
            MovieListItemBinding itemBinding = MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MovieViewHolder(itemBinding, onMovieListener);
        } else {
            PopularItemListViewBinding itemBinding = PopularItemListViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new PopularMoviesViewHolder(itemBinding, onMovieListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int listType = getItemViewType(position);
        MovieModel movie = moviesList.get(position);
        float rating = movie.getVote_average() / 2;
        if(listType == ListType.SEARCH.getViewType()) {

            ((MovieViewHolder) holder).itemBinding.titleTv.setText(movie.getTitle());

            // vote average is 0/10 and ratings bar is out of 5
            ((MovieViewHolder) holder).itemBinding.ratingBar.setRating(rating);

            // Display image using glide library
            Glide.with(((MovieViewHolder) holder).itemBinding.getRoot().getContext()).load(Credentials.BASE_IMAGE_URL + movie.getPoster_path()).into(((MovieViewHolder) holder).itemBinding.movieImg);
        } else {

            // vote average is 0/10 and ratings bar is out of 5
            ((PopularMoviesViewHolder) holder).itemBinding.ratingBar.setRating(rating);
            Log.wtf("RATING", rating + ": " + movie.getTitle());
            // Display image using glide library
            Glide
                    .with(((PopularMoviesViewHolder) holder).itemBinding.getRoot().getContext())
                    .load(Credentials.BASE_IMAGE_URL + movie.getPoster_path())
                    .into(((PopularMoviesViewHolder) holder).itemBinding.movieImg);
        }
    }

    @Override
    public int getItemCount() {
        if(moviesList != null) {
            return moviesList.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMoviesList(List<MovieModel> moviesList, ListType listType) {
        this.moviesList = moviesList;
        this.listType = listType;
        notifyDataSetChanged();
    }

    public MovieModel getSelectedMovie(int position) {
        if(moviesList != null) {
            if(moviesList.size() > 0) {
                return moviesList.get(position);
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if(listType == ListType.POPULAR) {
            return ListType.POPULAR.getViewType();
        } else {return ListType.SEARCH.getViewType();}
    }
}
