package com.tc2r.dreammovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tc2r.dreammovieapp.adapters.MovieRecyclerAdapter;
import com.tc2r.dreammovieapp.adapters.OnMovieListener;
import com.tc2r.dreammovieapp.databinding.ActivityMainBinding;
import com.tc2r.dreammovieapp.utils.ListType;
import com.tc2r.dreammovieapp.viewmodels.MovieListViewModel;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private MovieRecyclerAdapter movieRecyclerAdapter;
    // ViewModel
    private MovieListViewModel movieListViewModel;

    boolean isPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        try {
            Class.forName("dalvik.system.CloseGuard").getMethod("setEnabled", boolean.class).invoke(null, true);
        } catch(ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // SearchView
        SetupSearchView();

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        recyclerView = binding.recyclerView;
        ConfigureRecyclerView();

        // Observe for data changes.
        ObserveQueryMovieListChange();
        ObservePopularMoviesListChange();

        // Get Popular Movies Data from TMDB API
        movieListViewModel.getPopularMovies(1);
    }


    private void SetupSearchView() {
        final SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.getMoviesByQuery(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // If the user searches for a term, set isPopular to false.
        searchView.setOnSearchClickListener(view -> isPopular = false);
    }

    private void ConfigureRecyclerView() {
        movieRecyclerAdapter = new MovieRecyclerAdapter(this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Recyclerview Pagination
        // Loading next page of api request
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // If user has reached the bottom of page
                if(!recyclerView.canScrollVertically(1)) {
                    // Here we need to display the next search results on the next page of the api
                    movieListViewModel.searchNextPage();
                }
            }
        });
    }

    // Observing change in movies list from api.
    private void ObservePopularMoviesListChange() {
        movieListViewModel.getPopularMoviesLiveData().observe(this, movieModels -> {
            // Observing for any data change.
            if(movieModels != null) {
                movieRecyclerAdapter.setMoviesList(movieModels, ListType.POPULAR);
            }
        });
    }

    private void ObserveQueryMovieListChange() {
        movieListViewModel.getMoviesLiveData().observe(this, movieModels -> {
            // Observing for any data change.
            if(movieModels != null) {
                movieRecyclerAdapter.setMoviesList(movieModels, ListType.SEARCH);
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }
}
