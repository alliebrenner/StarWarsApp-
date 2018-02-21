package com.example.alliebrenner.starwarsapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter{
    private Context myContext;
    private ArrayList<Movie> myMovieList;
    private LayoutInflater myInflater;

    public MovieAdapter(Context myContext,ArrayList<Movie> myMovieList){
        //constructor
        this.myContext = myContext;
        this.myMovieList = myMovieList;
        myInflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //methods
    @Override
    public int getCount(){
        return myMovieList.size();
    }
    @Override
    public Object getItem(int position){
        return myMovieList.get(position);
    }

    @Override
    public long getItemId (int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            convertView = myInflater.inflate(R.layout.list_item_movie,parent,false);
            holder= new ViewHolder();

            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.mainCharactersTextView = convertView.findViewById(R.id.movie_list_main_character);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            holder.posterImageView = convertView.findViewById(R.id.movie_list_poster);
            holder.hasSeenTextView = convertView.findViewById(R.id.result_text_view);



            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder)convertView.getTag();
        }


            TextView titleTextView = holder.titleTextView;
            TextView mainCharactersTextView = holder.mainCharactersTextView;
            TextView descriptionTextView = holder.descriptionTextView;
            ImageView posterImageView = holder.posterImageView;
            TextView hasSeenTextView = holder.hasSeenTextView;

            Movie movie = (Movie)getItem(position);

            hasSeenTextView.setTextSize(10);

            //titleTextView
            titleTextView.setText(movie.title);
            titleTextView.setTextColor(ContextCompat.getColor(myContext,R.color.colorAccent));
            titleTextView.setTextSize(20);

            descriptionTextView.setText(movie.description);
            descriptionTextView.setTextColor(ContextCompat.getColor(myContext,R.color.colorPrimaryDark));
            descriptionTextView.setTextSize(9);

        mainCharactersTextView.setText("Main Characters: " +movie.mainCharacters.get(0)
        +", " +movie.mainCharacters.get(1)
                +", " +movie.mainCharacters.get(2));
        mainCharactersTextView.setTextColor(ContextCompat.getColor(myContext,R.color.colorPrimary));
        mainCharactersTextView.setTextSize(18);

        hasSeenTextView.setText(movie.hasSeen);

            Picasso.with(myContext).load(movie.posterUrl).into(posterImageView);

        return convertView;

    }
    private static class ViewHolder{
        public TextView titleTextView;
        public TextView mainCharactersTextView;
        public TextView descriptionTextView;
        public TextView hasSeenTextView;
        public ImageView posterImageView;

    }

}




