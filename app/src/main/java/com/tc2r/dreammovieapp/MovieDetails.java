package com.tc2r.dreammovieapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.tc2r.dreammovieapp.databinding.ActivityMovieDetailsBinding;
import com.tc2r.dreammovieapp.models.MovieModel;
import com.tc2r.dreammovieapp.utils.Credentials;

public class MovieDetails extends AppCompatActivity {
    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        GetDataFromIntent();
    }

    private void GetDataFromIntent() {
        if(getIntent().hasExtra("movie")) {

            MovieModel movie = getIntent().getParcelableExtra("movie");
            binding.overviewTv.setText(movie.getOverview());
            binding.titleTv.setText(movie.getTitle());
            binding.ratingBar.setRating(movie.getVote_average() / 2);

            Glide.with(this).load(Credentials.BASE_IMAGE_URL + movie.getPoster_path()).into(binding.detailsIv);
        }
    }
}
