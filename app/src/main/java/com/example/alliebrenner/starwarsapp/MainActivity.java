package com.example.alliebrenner.starwarsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView myListView;
    private Context myContext;
    private TextView resultTextView;
    private ArrayList<Movie> movieList;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContext = this;
        movieList= Movie.getMoviesFromFile("movies.json",this);


          adapter = new MovieAdapter(this,movieList);

        myListView = findViewById(R.id.movie_list_view);
        myListView.setAdapter(adapter);




        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie selectedMovie = movieList.get(position);

                //pass row number in intent to detail activity then to

                Intent detailIntent = new Intent(myContext,MovieDetailActivity.class);
                detailIntent.putExtra("title",selectedMovie.title);
                detailIntent.putExtra("main_characters",selectedMovie.mainCharacters);
                detailIntent.putExtra("description",selectedMovie.description);
                detailIntent.putExtra("poster",selectedMovie.posterUrl);
                detailIntent.putExtra("url",selectedMovie.url);
                detailIntent.putExtra("position",position);

                launchActivity(detailIntent);

            }

        });


    }

    public void launchActivity(Intent intent){
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {


                final int position = data.getIntExtra("position", -1);
                boolean seenChecked = data.getBooleanExtra("seen_check",false);
                boolean wantChecked = data.getBooleanExtra("want_to_see_check",false);
                boolean dontWantCheck = data.getBooleanExtra("dont_like_check",false);

                //then disp different strings in the text view


                if(seenChecked){
                    movieList.get(position).hasSeen=("Already Seen");

                }
                else if (wantChecked){
                    movieList.get(position).hasSeen=("Want to See");
                }
                else if (dontWantCheck){
                    movieList.get(position).hasSeen=("Don't Want to See");

            }


                adapter.notifyDataSetChanged();

            }
        }
    }
}
