package com.ai;

public abstract class Metric {

    public abstract int estimateDistanceToSolutionState(State current, State solutionState);
}
