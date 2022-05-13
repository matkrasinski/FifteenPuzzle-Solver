package com.ai;

import java.util.*;

public class BFS extends Strategy{

    public Queue<State> visited;

    public BFS(State solutionState, State initialState) {
        super(solutionState, initialState);
    }

//    public State solutionState;
//    public State initialState;
//    public String solutionPath = "";
//    public Set<State> closed;
//    public Queue<State> visited;
//
//    public BFS(State goalState, State initState) {
//        this.solutionState = goalState;
//        this.initialState = initState;
//    }

    @Override
    public State findSolution(char[] order) {
        long start = System.nanoTime();
        if (isSolved(initialState, solutionState)) {
            data = new Data(0);
            return initialState;
        }
        visited = new LinkedList<>();
        closed = new HashSet<>();
        visited.add(initialState);
        closed.add(initialState);
        int visitedSize = 0;
        int closedSize = 0;
        long end;
        while (!visited.isEmpty()) {
            State currentNode = visited.remove();
            closedSize++;
            max = currentNode.depth;
            for (State n : currentNode.generateNeighbours(order)) {
                if (isSolved(n, solutionState)) {
                    solutionPath = Path.getSolutionPath(n, closed);
                    //System.out.println(solutionPath);
                    max = n.depth;
                    end = System.nanoTime();
                    data = new Data(visitedSize, closedSize, max, end - start, solutionPath);
                    solutionPath = "";
                    return n;
                }
                if (!closed.contains(n)) {
                    visited.add(n);
                    closed.add(n);
                }
                visitedSize++;
            }
        }
        data = new Data(-1);
        return null;
    }

}
