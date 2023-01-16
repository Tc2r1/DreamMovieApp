package com.tc2r.dreammovieapp.request;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tc2r.dreammovieapp.AppExecutors;
import com.tc2r.dreammovieapp.models.MovieModel;
import com.tc2r.dreammovieapp.response.MovieSearchResponse;
import com.tc2r.dreammovieapp.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

/**
 * null.java
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieApiClient {

    private static MovieApiClient instance;

    // Live Data for search
    private final MutableLiveData<List<MovieModel>> mMovies;

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    // Live Data for popular
    private final MutableLiveData<List<MovieModel>> popularMovies;

    public LiveData<List<MovieModel>> getPopularMovies() {return popularMovies;}

    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    private RetrievePopularMoviesRunnable retrievePopularMoviesRunnable;


    public static MovieApiClient getInstance() {
        if(instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        this.mMovies = new MutableLiveData<>();
        this.popularMovies = new MutableLiveData<>();
    }


    public void getMoviesByQuery(String query, int pageNumber) {

        if(retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future<?> myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call.
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MILLISECONDS);
    }

    public void getPopularMovies(int pageNumber) {
        if(retrievePopularMoviesRunnable != null) {
            retrievePopularMoviesRunnable = null;
        }

        retrievePopularMoviesRunnable = new RetrievePopularMoviesRunnable(pageNumber);

        final Future<?> popularHandler = AppExecutors.getInstance().networkIO().submit(retrievePopularMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call.
                popularHandler.cancel(true);
            }
        }, 4000, TimeUnit.MILLISECONDS);
    }


    // Retrieving data from RESTAPI by runnable class
    private class RetrievePopularMoviesRunnable implements Runnable {

        private final int pageNumber;
        boolean cancelRequest;

        public RetrievePopularMoviesRunnable(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        @Override
        public void run() {
            // Get the response objects
            try {
                Response<MovieSearchResponse> popularResponse = getPopularMovies(pageNumber).execute();

                if(cancelRequest) {
                    return;
                }

                if(popularResponse.code() == 200) {
                    List<MovieModel> movieList = new ArrayList<>(popularResponse.body().getMovies());

                    if(pageNumber == 1) {
                        // Sending data to live data
                        // Postvalue: used for background thread
                        // setvalue: not for background thread
                        popularMovies.postValue(movieList);
                    } else {
                        List<MovieModel> currentMovies = popularMovies.getValue();
                        currentMovies.addAll(movieList);
                        popularMovies.postValue(currentMovies);
                    }
                } else {
                    String error = popularResponse.message();
                    Log.d("popularMovies", "Error: " + error);
                    mMovies.postValue(null);
                }
            } catch(IOException e) {
                Log.d("popularMovies", "Error Message: " + e.getMessage());
                mMovies.postValue(null);
            }
        }

        // Search method / query
        private Call<MovieSearchResponse> getPopularMovies(int pageNumber) {
            return MovieService.getMovieAPI().getPopularMovies(Credentials.API_KEY, pageNumber);
        }

        private void cancelRequest() {
            Log.v("TAG", "Cancelling Request");
            cancelRequest = true;
        }
    }

    private class RetrieveMoviesRunnable implements Runnable {

        private final String query;
        private final int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            // Get the response objects
            try {
                Response<MovieSearchResponse> response = getMovies(query, pageNumber).execute();

                if(cancelRequest) {
                    return;
                }

                if(response.code() == 200 && response.body() != null) {
                    List<MovieModel> movieList = new ArrayList<>(response.body().getMovies());

                    if(pageNumber == 1) {
                        // Sending data to live data
                        // Postvalue: used for background thread
                        // setvalue: not for background thread
                        mMovies.postValue(movieList);
                    } else {
                        List<MovieModel> currentMovies = mMovies.getValue();

                        if(currentMovies != null) {
                            currentMovies.addAll(movieList);
                            mMovies.postValue(currentMovies);
                        }
                    }
                } else {
                    String error = response.message();
                    Log.d("searchMovies", "Error: " + error);
                    mMovies.postValue(null);
                    cancelRequest();
                }
            } catch(IOException e) {
                Log.d("searchMovies", "Error Message: " + e.getMessage());
                mMovies.postValue(null);
            }
        }

        // Search method / query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return MovieService.getMovieAPI().searchMovie(Credentials.API_KEY, query, pageNumber);
        }

        private void cancelRequest() {
            Log.v("TAG", "Cancelling Request");
            cancelRequest = true;
        }
    }
}
