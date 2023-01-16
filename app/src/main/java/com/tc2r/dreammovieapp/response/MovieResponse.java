package com.tc2r.dreammovieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tc2r.dreammovieapp.models.MovieModel;

/**
 * This class is for single movie request
 * <p>
 * Uses a singleton pattern for retrofit.
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieResponse {
    // 1 - finding the movie object

    @SerializedName("results") @Expose private MovieModel movie;

    public MovieModel getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" + "movie=" + movie + '}';
    }
}
