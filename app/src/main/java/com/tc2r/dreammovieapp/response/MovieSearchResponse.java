package com.tc2r.dreammovieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tc2r.dreammovieapp.models.MovieModel;

import java.util.List;

/**
 * This class is for getting multiple movies (Movies list) - popular movies.
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieSearchResponse {

    @SerializedName("total_results") @Expose() private int total_count;

    // Because results is an array of movies, we need to create a list of movies
    @SerializedName("results") @Expose() private List<MovieModel> movies;

    public int getTotal_count() {
        return total_count;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" + "total_count=" + total_count + ", movies=" + movies + '}';
    }
}
