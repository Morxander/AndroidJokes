package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.morxander.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import morxander.androidjokes.MainActivityJokes;

/**
 * Created by morxander on 10/30/15.
 */
class EndpointsAsyncTask extends AsyncTask<com.google.appengine.repackaged.com.google.common.base.Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private IAsyncTaskListener mListener = null;
    private Exception mError = null;

    @Override
    protected String doInBackground(com.google.appengine.repackaged.com.google.common.base.Pair<Context, String>... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-1115.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        if(params.length > 0){
        context = params[0].first;
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public EndpointsAsyncTask setListener(IAsyncTaskListener listener) {
        this.mListener = listener;
        return this;
    }

    @Override
    protected void onPostExecute(String result) {
        if (context != null) {
            Intent intent = new Intent(context,MainActivityJokes.class);
            intent.putExtra("joke",result);
            context.startActivity(intent);
        }
        if (this.mListener != null)
            this.mListener.onComplete(result, mError);
    }

    @Override
    protected void onCancelled() {
        if (this.mListener != null) {
            mError = new InterruptedException("AsyncTask cancelled");
            this.mListener.onComplete(null, mError);
        }
    }
}