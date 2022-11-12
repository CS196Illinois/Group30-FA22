package com.mygdx.game;

import fj.*;

public class FjImplementationTest {
    private final int target;
    private final int user;
    public FjImplementationTest(int targ, int input) {
        target = targ;
        user = input;
    }

    public int magic() {
       final F<Integer, Integer> m = i -> i*2;

       return (int) Math.pow(2.0, m.f(user)/2.0);
    }

    public boolean testImplementation() {
        return magic() == target;
    }


}
