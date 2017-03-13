package erick.munoz.guerrero.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import erick.munoz.guerrero.listeners.NetworkConnectionInterface;
import erick.munoz.guerrero.respandomovies.R;

/**
 * Created by Lenovo-PC on 11/03/2017.
 */

public class NetworkConnection extends AsyncTask<Void,Void,Boolean>{
    private final String Tag = NetworkConnection.class.getSimpleName();
    private Context context;
    private String response;
    private NetworkConnectionInterface listener;

    public NetworkConnection(Context context, NetworkConnectionInterface networkConnectionInterface){
        this.context=context;
        this.listener=networkConnectionInterface;
    }


    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean respuesta=false;
        final String BASE_URL="http://api.themoviedb.org/3/movie";
        final String POPULAR_PATH="popular";
        final String API_KEY_PARAM="api_key";

        //construcción url
        Uri uriToAPI = Uri.parse(BASE_URL).buildUpon()
                .appendPath(POPULAR_PATH)
                .appendQueryParameter(API_KEY_PARAM, context.getString(R.string.api_keu_value))
                .build();
        Log.d(Tag,uriToAPI.toString());
        HttpURLConnection urlConnection;
        BufferedReader reader;

        try {
            URL url = new URL(uriToAPI.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream==null){
                return false;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line=reader.readLine())!=null){
                buffer.append(line+"\n");
            }

            if(buffer.length()==0){/////no tiene lineas.
                return false;
            }

            response = buffer.toString();
            Log.d(Tag,"Server response: "+response);
            respuesta=true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(Tag,e.toString());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(Tag,e.toString());
            return false;
        }


        return respuesta;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            if(listener!=null){
                listener.OnSuccessfullyResponse(response);
            }
        }else{
            if(listener!=null){
                listener.OnFailedResponse();
            }
        }


    }
}
