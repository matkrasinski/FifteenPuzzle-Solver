package com.ai;

import java.util.*;

public class BFS extends Strategy{

    public Queue<State> visited;

    public BFS(State solutionState, State initialState) {
        super(solutionState, initialState);
    }

    @Override
    public State findSolution(char[] order) {
        long start = System.nanoTime();
        if (isSolved(initialState, solutionState)) {
            data = new Data(0);
            return initialState;
        }
        int visitedSize = 0;
        int closedSize = 0;
        visited = new LinkedList<>();
        closed = new HashSet<>();
        visited.add(initialState);
        closed.add(initialState);

        long end;
        while (!visited.isEmpty()) {
            State currentNode = visited.remove();
            closedSize++;
            max = currentNode.depth;
            for (State n : currentNode.generateNeighbours(order)) {
                if (isSolved(n, solutionState)) {
                    solutionPath = Path.getSolutionPath(n, closed);
                    max = n.depth;
                    end = System.nanoTime();
                    data = new Data(visitedSize, closedSize, max, end - start, solutionPath);
                    solutionPath = "";
                    return n;
                }
                if(!closed.contains(n)) {
                    visited.add(n);
                    visitedSize++;
                    closed.add(n);
                }
            }
        }
        data = new Data(-1);
        return null;
    }

}
