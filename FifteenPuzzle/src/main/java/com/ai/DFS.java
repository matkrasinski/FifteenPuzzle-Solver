package com.ai;

import java.util.*;

public class DFS {
    public State solutionState;
    public State initialState;
    public String solutionPath = "";
    public Stack<State> visited;
    public Set<State> closed;
    public boolean result;

    public DFS(State goalState, State initState) {
        this.solutionState = goalState;
        this.initialState = initState;
    }
    public State findSolution(char[] order) {
        if (Arrays.deepEquals(initialState.puzzle, solutionState.puzzle)){
            return initialState;
        }
        int maxDepth = 25;
        List<State> neighbours;
        visited = new Stack<>();
        closed = new HashSet<>();
        visited.push(initialState);

        while (!visited.isEmpty()) {
            State currentNode = visited.pop();
            if (!closed.contains(currentNode) && currentNode.depth <  maxDepth) {
                closed.add(currentNode);
                neighbours = currentNode.generateNeighbours(order);
                Collections.reverse(neighbours);
                for (State n : neighbours) {
                    if (Arrays.deepEquals(n.puzzle, solutionState.puzzle)){
                        getSolutionPath(n);
                        System.out.println(solutionPath);
                        solutionPath = "";
                        return n;
                    }
                    visited.push(n);
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
