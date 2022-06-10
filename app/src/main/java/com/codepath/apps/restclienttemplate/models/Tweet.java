package com.codepath.apps.restclienttemplate.models;

import static com.facebook.stetho.inspector.network.ResponseHandlingInputStream.TAG;

import android.util.Log;

import com.facebook.stetho.inspector.network.ResponseHandlingInputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Parcel

public class Tweet {
    public String body;
    public String CreatedAt;
    public User user;
    public String imageURL;


    //empty constructor needed by the Parceler library

    public Tweet(){

    }
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        if(jsonObject.has("full_text")) {
            tweet.body = jsonObject.getString("full_text");
        } else {
            tweet.body = jsonObject.getString("text");
        }

        if(jsonObject.getJSONObject("entities").has("media")){
            tweet.imageURL= jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
        }

        tweet.CreatedAt= jsonObject.getString("created_at");
        tweet.user = (User) User.fromJson(jsonObject.getJSONObject("user"));


        return tweet;
    }



    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i=0; i< jsonArray.length(); i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));

        }
        return tweets;

    }






}