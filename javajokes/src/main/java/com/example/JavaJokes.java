package com.example;

import java.util.Random;

public class JavaJokes {
    private String[] jokes= {"Who does a pharaoh talk to when he's sad? His mummy.",
                             "What do you call a fish with no eye? Fssshh",
                            "Why is there no gambling in Africa? Too many Cheetahs!",
                            " Why couldn't Dracula's wife get to sleep? Because of his coffin."};
    public String tellMeJoke(){
        int num = new Random().nextInt(jokes.length);
        return jokes[num];
    }
}
