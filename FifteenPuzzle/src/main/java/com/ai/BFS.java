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
        visited = new LinkedList<>();
        closed = new HashSet<>();
        visited.add(initialState);
        int visitedSize = 1;
        int closedSize = 1;
        long end;
        while (!visited.isEmpty()) {
            State currentNode = visited.remove();
            max = currentNode.depth;
            if(!closed.contains(currentNode)) {
                closed.add(currentNode);
                visitedSize++;
            }
            for (State n : currentNode.generateNeighbours(order)) {
                if (isSolved(n, solutionState)) {
                    solutionPath = Path.getSolutionPath(n, closed);
                    max = n.depth;
                    end = System.nanoTime();
                    data = new Data(visitedSize, closedSize, max, end - start, solutionPath);
                    solutionPath = "";
                    return n;
                }
                if(!closed.contains(n) && !visited.contains(n)) {
                    visited.add(n);
                    closedSize++;
                }
            }
        }
        data = new Data(-1);
        return null;
    }

}
