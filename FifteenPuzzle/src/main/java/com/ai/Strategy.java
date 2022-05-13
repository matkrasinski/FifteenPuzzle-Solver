package com.ai;

import java.util.Arrays;
import java.util.Set;

public abstract class Strategy {

    public State solutionState;
    public State initialState;
    public String solutionPath = "";
    public Set<State> closed;
    public int max;

    public Data data;

    public Strategy(State solutionState, State initialState) {
        this.solutionState = solutionState;
        this.initialState = initialState;
    }

    public State findSolution(char[] order) {
        return null;
    }

    public boolean isSolved(State currentNode, State solutionState) {
        return Arrays.deepEquals(currentNode.puzzle, solutionState.puzzle);
    }

}
