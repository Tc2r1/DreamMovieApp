package com.tc2r.dreammovieapp.utils;

import com.tc2r.dreammovieapp.models.MovieModel;
import com.tc2r.dreammovieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * MovieAPI.java.java
 * Dream App Developments
 * <p>
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public interface MovieAPI {


    // Search for movies
    // https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false

    @GET("search/movie?")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String key, @Query("query") String query, @Query("page") int page

    );

    // Get Popular Movies List
    //  https://api.themoviedb.org/3/movie/popular
    @GET("movie/popular")
    Call<MovieSearchResponse> getPopularMovies(@Query("api_key") String key, @Query("page") int page

    );

    // Search for movie by ID
    // https://api.themoviedb.org/3/movie/{MOVIE_ID}?api_key=<<api_key>>

    @GET("movie/{movie_id}?")
    Call<MovieModel> getMovie(@Path("movie_id") int movie_id, @Query("api_key") String api_key);
}
