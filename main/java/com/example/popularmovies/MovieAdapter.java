package com.example.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.Model.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.squareup.picasso.Picasso.with;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private MainActivity.OnItemClickListener listener;


    private List<Model> dataModelArrayList;

    public MovieAdapter(List<Model> dataModelArrayList, MainActivity.OnItemClickListener listener){
        this.dataModelArrayList=dataModelArrayList;
        this.listener = listener;

    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_list_item,viewGroup,false);
        MovieViewHolder holder=new MovieViewHolder(view);
        return holder;
    }
    @Override
     public void onBindViewHolder(final MovieViewHolder holder, int position){
        if (dataModelArrayList.get( position ).getPoster_path() != null) {
            Picasso.with( holder.itemView.getContext() )
                    .load( Uri.parse( dataModelArrayList.get( position ).getPoster_path()  ) )
                    .into( holder.imageView );
        }

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick( dataModelArrayList.get( holder.getAdapterPosition() ) );
            }
        } );
        holder.itemView.setOnCreateContextMenuListener( new View.OnCreateContextMenuListener(){
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            }

        }  );
        holder.title.setText(dataModelArrayList.get(position).getTitle());
        //holder.overview.setText(dataModelArrayList.get(position).getOverview());
        //holder.vote.setText(dataModelArrayList.get(position).getVote());
        //holder.release_date.setText(dataModelArrayList.get(position).getRelease_date());

    }


    @Override
    public  int getItemCount(){
        return dataModelArrayList.size();
    }
    class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView title,overview,vote,release_date;
        ImageView imageView;

        public MovieViewHolder(View itemView){
            super(itemView);

            title= itemView.findViewById(R.id.tv_title);
            overview= itemView.findViewById(R.id.tv_overview_detail);
            vote= itemView.findViewById(R.id.tv_vote_detail);
            release_date= itemView.findViewById(R.id.tv_release_date_detail);
            imageView=itemView.findViewById(R.id.iv_poster_display);
            }
        }
    }