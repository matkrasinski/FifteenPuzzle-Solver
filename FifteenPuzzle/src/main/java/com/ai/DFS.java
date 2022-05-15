package com.ai;

import java.util.*;

public class DFS extends Strategy{


    public DFS(State solutionState, State initialState) {
        super(solutionState, initialState);
    }

    public Stack<State> visited;

    @Override
    public State findSolution(char[] order) {
        long start = System.nanoTime();
        int maxDepth = 22;
        int visitedSize = 0;
        int closedSize = 0;
        List<State> neighbours;
        visited = new Stack<>();
        closed = new HashSet<>();
        visited.push(initialState);
        visitedSize++;
        long end;

        while (!visited.isEmpty()) {
            State currentNode = visited.pop();
            if (isSolved(currentNode, solutionState)){
                solutionPath = Path.getSolutionPath(currentNode, closed);
                max = currentNode.depth;
                end = System.nanoTime();
                data = new Data( visitedSize, closedSize, max, end - start, solutionPath);
                solutionPath = "";
                return currentNode;
            }
            if(!closed.contains(currentNode) && currentNode.depth < maxDepth) {
                closed.add(currentNode);
                closedSize++;

                neighbours = currentNode.generateNeighbours(order);
                Collections.reverse(neighbours);
                for (State n : neighbours) {
                    visited.push(n);
                    visitedSize++;
                }
            }
        }
        data = new Data(-1);
        return null;
    }


}
