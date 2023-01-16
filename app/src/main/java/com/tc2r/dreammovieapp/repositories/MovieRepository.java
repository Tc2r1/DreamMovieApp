package com.tc2r.dreammovieapp.repositories;

import androidx.lifecycle.LiveData;

import com.tc2r.dreammovieapp.models.MovieModel;
import com.tc2r.dreammovieapp.request.MovieApiClient;

import java.util.List;

/**
 * This class performs as a repository.
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieRepository {

    private static MovieRepository instance;

    private final MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance() {
        if(instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMoviesLiveData() {return movieApiClient.getMovies();}

    public LiveData<List<MovieModel>> getPopularMoviesLiveData() {return movieApiClient.getPopularMovies();}


    public void getMoviesByQuery(String query, int page) {
        mQuery = query;
        mPageNumber = page;
        movieApiClient.getMoviesByQuery(query, page);
    }

    public void getPopularMovies(int page) {
        mPageNumber = page;
        movieApiClient.getPopularMovies(page);
    }

    public void searchNextPage() {
        int nextPage = mPageNumber + 1;
        if(mQuery != null) {
            getMoviesByQuery(mQuery, nextPage);
        } else {
            getPopularMovies(nextPage);
        }
    }
}
