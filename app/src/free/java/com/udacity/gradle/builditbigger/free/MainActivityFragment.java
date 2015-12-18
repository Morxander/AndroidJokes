package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.JavaJokes;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.appengine.repackaged.com.google.common.base.Pair;
import com.udacity.gradle.builditbigger.R;

import morxander.androidjokes.MainActivityJokes;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    Button tellJoke,joke_screen;
    JavaJokes jokes;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        tellJoke = (Button)root.findViewById(R.id.tell_joke);
        joke_screen = (Button)root.findViewById(R.id.joke_screen);
        jokes = new JavaJokes();
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),jokes.tellMeJoke(),Toast.LENGTH_LONG).show();
                new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), ""));
            }
        });
        joke_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivityJokes.class);
                intent.putExtra("joke",jokes.tellMeJoke());
                startActivity(intent);
            }
        });
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        super.onCreate(savedInstanceState);
        return root;
    }
}
