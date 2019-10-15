package com.example.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.popularmovies.Api;


public class Model implements Parcelable {

        private String title;
        private String release_date;
        private String poster_path;
        private String vote_average;
        private String overview;

    public Model() {
        }

    public Model(String title, String release_date, String poster_path, String vote_average, String overview) {
            this.title = title;
            this.release_date = release_date;
            this.poster_path = poster_path;
            this.vote_average = vote_average;
            this.overview = overview;
        }

        public String getTitle () {
            return title;
        }

        public void setTitle (String title){
            this.title = title;
        }

        public String getRelease_date () {
            return release_date;
        }

        public void setRelease_date (String release_date){
            this.release_date = release_date;
        }


        public String getPoster_path () {
            return Api.IMAGE_URL + poster_path;
        }

        public void setPoster_path (String poster_path){
            this.poster_path = poster_path;
        }

        public String getVote () {
            return vote_average;
        }
        public void setVote (String vote_average){
            this.vote_average = vote_average;
        }

        public String getOverview () {
            return overview;
        }
        public void setOverview (String overview){
            this.overview = overview;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.title );
        dest.writeString( this.release_date );
        dest.writeString( this.poster_path );
        dest.writeString( this.vote_average );
        dest.writeString( this.overview );
    }

    protected Model(Parcel in) {
        this.title=in.readString();
        this.release_date=in.readString();
        this.poster_path=in.readString();
        this.vote_average=in.readString();
        this.overview=in.readString();
    }

    public static final Parcelable.Creator<Model> CREATOR=new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model( source );
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}
