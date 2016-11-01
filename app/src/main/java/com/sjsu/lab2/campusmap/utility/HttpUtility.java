package com.sjsu.lab2.campusmap.utility;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.sjsu.lab2.campusmap.model.BuildingDetail;
import com.sjsu.lab2.campusmap.activity.DetailActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtility extends AsyncTask<String, Void, String>{

    private Context ctx;
    private StringBuffer response;
    private String distance, duration, abbr;

    public HttpUtility(Context context){
        this.ctx = context;
    }
    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection;

        try{
            url = new URL(params[0]);
            abbr = params[1];
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                response = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null)
                    response.append(line);
            }
        }catch( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        try{
            JSONObject distanceJSON = new JSONObject(response.toString());
            distance = distanceJSON.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");
            JSONObject durationJSON = new JSONObject(response.toString());
            duration = durationJSON.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("duration").getString("text");
            //Fetching destination building object
            BuildingDetail Destination = MapUtility.seacrhFor(abbr);

            Intent intent = new Intent(ctx, DetailActivity.class);
            intent.putExtra("Name", Destination.getName());
            intent.putExtra("Destination", Destination.getAddress());
            intent.putExtra("Distance", distance);
            intent.putExtra("Duration", duration);
            intent.putExtra("Image", Destination.getBuildinngImage());
            //start new activity
            ctx.startActivity(intent);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
