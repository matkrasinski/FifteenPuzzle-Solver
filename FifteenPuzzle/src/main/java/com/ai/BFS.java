package com.ai;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public State solutionState;
    public State initialState;
    public char[] neighborhoodSearchOrder;

    public BFS(State goalState, State initState, char[] searchOrder) {
        solutionState = goalState;
        initialState = initState;
        neighborhoodSearchOrder = searchOrder;
    }

    public String FindSolution() {

        StringBuilder solution = new StringBuilder();

        if (this.initialState.isPuzzleSame(this.solutionState)) {
            solution = new StringBuilder("NO STEPS NEEDED");
        }

        Queue<State> toVisit = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        visited.add(this.initialState);
        toVisit.add(this.initialState);

        while(!toVisit.isEmpty()) {

            State currentState = toVisit.poll();
            currentState.generateNeighbours(neighborhoodSearchOrder);

            for (State n : currentState.neighbours) {
                if (n.isPuzzleSame(this.solutionState)) {
                    return n.solution;
                }
                if (!visited.contains(n)) {
                    visited.add(n);
                    toVisit.add(n);
                }

            }
        }
        return solution.toString();

    }
}
