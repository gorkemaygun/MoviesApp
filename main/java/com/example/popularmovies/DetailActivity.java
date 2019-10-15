package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.Model.Model;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION="MODEL";
    private ImageView mImageDisplay;
    private TextView mTitleDisplay;
    private TextView mReleaseDateDisplay;
    private TextView mOverviewDisplay;
    private TextView mVoteDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        
        Model model = getIntent().getParcelableExtra( EXTRA_POSITION );

        mImageDisplay=findViewById(R.id.iv_poster_display);
        mTitleDisplay=findViewById(R.id.tv_title_detail);
        mOverviewDisplay=findViewById(R.id.tv_overview_detail);
        mReleaseDateDisplay=findViewById(R.id.tv_release_date_detail);
        mVoteDisplay=findViewById(R.id.tv_vote_detail);

        mVoteDisplay.setText( String.format( getString( R.string.vote_average ) ,model.getVote()) );
        mReleaseDateDisplay.setText( String.format( getString( R.string.release_date ), model.getRelease_date() ) );
        mOverviewDisplay.setText(String.format( getString( R.string.overview ) , model.getOverview() ) );
        mTitleDisplay.setText(String.format( getString( R.string.title ), model.getTitle()) );

        if (model.getPoster_path() != null) {
            Picasso.with(this)
                    .load( model.getPoster_path()  )
                    .into( mImageDisplay);
        }
            
    }
}
