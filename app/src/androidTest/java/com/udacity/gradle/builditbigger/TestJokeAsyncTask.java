package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import junit.framework.Assert;

import java.util.concurrent.CountDownLatch;

/**
 * Created by morxander on 10/31/15.
 */
public class TestJokeAsyncTask extends AndroidTestCase{

    String results = null;
    Exception mError = null;
    CountDownLatch signal = null;

    @UiThreadTest
    public void testAsyncTask() {
        signal = new CountDownLatch(1);
        EndpointsAsyncTask jokeAsyncTask = new EndpointsAsyncTask();
        assertTrue(1==1);
        jokeAsyncTask.setListener(new IAsyncTaskListener() {
            @Override
            public void onComplete(String returnedString, Exception e) {
                results = returnedString;
                mError = e;
                signal.countDown();
            }
        }).execute();
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertNull(mError);
        Assert.assertNotNull(results);

    }

}
