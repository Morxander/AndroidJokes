package com.udacity.gradle.builditbigger;

/**
 * Created by morxander on 10/31/15.
 */
public interface IAsyncTaskListener {
    public void onComplete(String jsonString, Exception e);
}
