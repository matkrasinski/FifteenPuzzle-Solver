package com.ai;

import java.util.*;

public class BFS {

    public State solutionState;
    public State initialState;
    public String solutionPath = "";
    public Set<State> closed;
    public Queue<State> visited;
    public boolean result;


    public BFS(State goalState, State initState) {
        this.solutionState = goalState;
        this.initialState = initState;
    }
    public State findSolution(char[] order) {
        if (Arrays.deepEquals(initialState.puzzle, solutionState.puzzle)) {
            return initialState;
        }
        visited = new LinkedList<>();
        closed = new HashSet<>();
        visited.add(initialState);
        closed.add(initialState);
        while (!visited.isEmpty()) {
            State currentNode = visited.remove();
            for (State n : currentNode.generateNeighbours(order)) {
                if (Arrays.deepEquals(n.puzzle, solutionState.puzzle)) {
                    getSolutionPath(n);
                    System.out.println(solutionPath);
                    return n;
                }
                if (!closed.contains(n)) {
                    visited.add(n);
                    closed.add(n);
                }
            }
        }
        return null;

    }

    public void getSolutionPath(State current) {
        StringBuilder stringBuilder = new StringBuilder(solutionPath);
        while (current.havePrev) {
            stringBuilder.append(current.step);
            for (State n : closed) {
                if (current.prevId == n.id) {
                    current = n;
                    break;
                }
            }
        }
        solutionPath = stringBuilder.reverse().toString();
    }
}
