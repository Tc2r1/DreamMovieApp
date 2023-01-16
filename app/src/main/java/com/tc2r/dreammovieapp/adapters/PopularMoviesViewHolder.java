package com.tc2r.dreammovieapp.adapters;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tc2r.dreammovieapp.databinding.PopularItemListViewBinding;

/**
 * PopularMoviesViewHolder.java
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/13/23.
 * Copyright © 2023. All rights reserved.
 */
public class PopularMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    PopularItemListViewBinding itemBinding;


    // Click Listener
    OnMovieListener onMovieListener;

    public PopularMoviesViewHolder(@NonNull PopularItemListViewBinding itemBinding, OnMovieListener onMovieListener) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
        this.onMovieListener = onMovieListener;

        itemBinding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
