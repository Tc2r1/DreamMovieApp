package com.tc2r.dreammovieapp.request;

import com.tc2r.dreammovieapp.utils.Credentials;
import com.tc2r.dreammovieapp.utils.MovieAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * null.java
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieService {

    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Credentials.BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final MovieAPI movieAPI = retrofit.create(MovieAPI.class);

    public static MovieAPI getMovieAPI() {
        return movieAPI;
    }
}
