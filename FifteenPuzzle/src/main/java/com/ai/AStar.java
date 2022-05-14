package com.ai;

import java.util.*;

public class AStar extends Strategy{

    public Metric metric;
    public Queue<State> visited;

    public AStar(State solutionState, State initialState, String metric) {
        super(solutionState, initialState);
        if(metric.equals("hamm")) {
            this.metric = new Hamming();
        } else if(metric.equals("manh")) {
            this.metric = new Manhattan();
        }
    }
    @Override
    public State findSolution(char[] order) {
        long start = System.nanoTime();
        if (Arrays.deepEquals(initialState.puzzle, solutionState.puzzle)) {
            data = new Data(0);
            return initialState;
        }
        int visitedSize = 0;
        int closedSize = 0;
        visited = new PriorityQueue<>();
        closed = new HashSet<>();
        visited.add(new State(initialState, 0));
        visitedSize++;
        long end;

        while(!visited.isEmpty()) {
            State currentNode = visited.remove();
            max = currentNode.depth;
            if (isSolved(currentNode, solutionState)) {
                solutionPath = Path.getSolutionPath(currentNode, closed);
                max = currentNode.depth;
                end = System.nanoTime();
                data = new Data(visitedSize, closedSize, max, end - start, solutionPath);
                solutionPath = "";
                return currentNode;
            }
            closed.add(currentNode);
            closedSize++;
            for (State n : currentNode.generateNeighbours(order)) {
                if (!closed.contains(n)) {
                    int f = metric.estimateDistanceToSolutionState(n, solutionState);
                    if (!visited.contains(n)) {
                        visited.add(new State(n, f));
                        visitedSize++;
                    }
                    else
                        if (n.priority > f)
                            update(n, f);
                }
            }
        }
        data = new Data(-1);
        return null;
    }

    public void update(State n, int f) {
        for (State i : visited)
            if (i.equals(n))
                n.setPriority(f);
    }

}
