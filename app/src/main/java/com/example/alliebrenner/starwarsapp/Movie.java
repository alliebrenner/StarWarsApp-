package com.example.alliebrenner.starwarsapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


public class Movie {

    public String title;
    public ArrayList<String> mainCharacters = new ArrayList<>();
    public String description;
    public String posterUrl;
    public String url;
    public String hasSeen;

    public static ArrayList<Movie> getMoviesFromFile(String filename,Context context){
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try {
            String jsonString = loadJsonFromAsset("movies.json",context);
            JSONObject json = new JSONObject(jsonString);

            JSONArray movies = json.getJSONArray("movies");
            for (int i = 0; i< movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.posterUrl = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");
                movie.hasSeen="Have Seen?";

                movieList.add(movie);

                JSONArray jsonCharacters = (JSONArray)movies.getJSONObject(i).get("main_characters");
                for (int j=0; j<jsonCharacters.length();j++){
                    movie.mainCharacters.add(j,jsonCharacters.getString(j));
                }

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return movieList;
    }
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}

