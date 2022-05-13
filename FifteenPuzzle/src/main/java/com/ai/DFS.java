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
        if (Arrays.deepEquals(initialState.puzzle, solutionState.puzzle)){
            return initialState;
        }
        int maxDepth = 25;
        List<State> neighbours;
        visited = new Stack<>();
        closed = new HashSet<>();
        visited.push(initialState);
        long end;

        while (!visited.isEmpty()) {
            State currentNode = visited.pop();
            if (!closed.contains(currentNode) && currentNode.depth <  maxDepth) {
                max = currentNode.depth;
                closed.add(currentNode);
                neighbours = currentNode.generateNeighbours(order);
                Collections.reverse(neighbours);

                for (State n : neighbours) {
                    if (isSolved(n, solutionState)){
                        solutionPath = Path.getSolutionPath(n, closed);
                        System.out.println(solutionPath);
                        max = n.depth;
                        end = System.nanoTime();
                        data = new Data(n.depth, visited.size(), closed.size(), max, end - start);
                        solutionPath = "";
                        return n;
                    }
                    visited.push(n);
                }
            }
        }
        end = System.nanoTime();
        data = new Data(-1, visited.size(), closed.size(), max, end - start);
        return null;
    }


}
