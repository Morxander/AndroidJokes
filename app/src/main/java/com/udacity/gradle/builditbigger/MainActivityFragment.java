package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.JavaJokes;
import com.google.appengine.repackaged.com.google.common.base.Pair;

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
        super.onCreate(savedInstanceState);
        return root;
    }
}
