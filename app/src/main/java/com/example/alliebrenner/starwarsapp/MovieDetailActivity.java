package com.example.alliebrenner.starwarsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private Context myContext;
    private TextView titleText;
    private ImageView posterImage;
    private TextView descriptionText;

    private boolean seenChecked;
    private boolean wantChecked;
    private boolean dontLikeChecked;


    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        myContext = this;
        titleText = findViewById(R.id.title_detail);
        posterImage= findViewById(R.id.poster_detail);
        descriptionText= findViewById(R.id.description_detail);
        submitButton= findViewById(R.id.submit_button);



        String title = this.getIntent().getExtras().getString("title");
        String description = this.getIntent().getExtras().getString("description");
        Picasso.with(myContext).load(this.getIntent().getExtras().getString("poster")).into(posterImage);
        final int position = this.getIntent().getExtras().getInt("position");
        setTitle(title);
        descriptionText.setText(description);
        titleText.setText(title);




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //put 3 boolean values into intent
                Intent checkBoxIntent = new Intent();

                checkBoxIntent.putExtra("position",position);

                checkBoxIntent.putExtra("seen_check", seenChecked);
                checkBoxIntent.putExtra("_want_to_see_check", wantChecked);
                checkBoxIntent.putExtra("dont_like_check", dontLikeChecked);
                //send back to main activity


                setResult(RESULT_OK, checkBoxIntent);
                //tells android that u want to send intent back to main Activity
                finish();
            }
        });

    }
    public void seenChecked(View view){
         seenChecked= ((RadioButton)view).isChecked();

    }
    public void wantChecked(View view){
        wantChecked = ((RadioButton)view).isChecked();

    }
    public void dontLikeChecked(View view){
        dontLikeChecked=((RadioButton)view).isChecked();

}

}