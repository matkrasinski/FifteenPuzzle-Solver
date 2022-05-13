package com.ai;

import java.util.Set;

public class Path {

    public static String getSolutionPath(State current, Set<State> closed) {
        StringBuilder stringBuilder = new StringBuilder();
        while (current.havePrev) {
            stringBuilder.append(current.step);
            for (State n : closed) {
                if (current.prevId == n.id) {
                    current = n;
                    break;
                }
            }
        }
        return stringBuilder.reverse().toString();
    }
}
