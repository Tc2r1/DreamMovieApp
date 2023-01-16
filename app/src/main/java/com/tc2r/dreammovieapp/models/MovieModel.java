package com.tc2r.dreammovieapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/**
 * Dream App Developments
 * <p>
 * Created by Nudennie White aka Tc2r on 1/12/23.
 * Copyright © 2023. All rights reserved.
 */
public class MovieModel implements Parcelable {
    private final String title;
    private final String poster_path;
    private final String release_date;
    private final int id;
    private final float vote_average;

    private final int vote_count;
    private final String overview;

    private final int run_time;

    // Constructor

    public MovieModel(String title, String poster_path, String release_date, int movie_id, float vote_average, int vote_count, String movie_overview, int runTime) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = movie_id;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.overview = movie_overview;
        this.run_time = runTime;
    }




    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        id = in.readInt();
        vote_average = in.readFloat();
        vote_count = in.readInt();
        overview = in.readString();
        run_time = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public int getRun_time() {
        return run_time;
    }

    public int getVote_count() {
        return vote_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeInt(id);
        parcel.writeFloat(vote_average);
        parcel.writeInt(vote_count);
        parcel.writeString(overview);
        parcel.writeInt(run_time);
    }

    @Override
    public String toString() {
        return "MovieModel{" + "title='" + title + '\'' + ", poster_path='" + poster_path + '\'' + ", release_date='" + release_date + '\'' + ", movie_id=" + id + ", vote_average=" + vote_average + ", vote_count=" + vote_count + ", movie_overview='" + overview + '\'' + ", run_time=" + run_time + '}';
    }
}
