package com.tc2r.dreammovieapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tc2r.dreammovieapp.models.MovieModel;
import com.tc2r.dreammovieapp.repositories.MovieRepository;

import java.util.List;

/**
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieListViewModel extends ViewModel {
    private final MovieRepository movieRepository;

    // Constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMoviesLiveData() {
        return movieRepository.getMoviesLiveData();
    }

    public LiveData<List<MovieModel>> getPopularMoviesLiveData() {
        return movieRepository.getPopularMoviesLiveData();
    }


    public void getMoviesByQuery(String query, int pageNumber) {
        movieRepository.getMoviesByQuery(query, pageNumber);
    }

    public void getPopularMovies(int pageNumber) {
        movieRepository.getPopularMovies(pageNumber);
    }

    public void searchNextPage() {
        movieRepository.searchNextPage();
    }
}
