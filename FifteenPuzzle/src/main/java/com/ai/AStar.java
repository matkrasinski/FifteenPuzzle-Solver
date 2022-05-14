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
        visited = new PriorityQueue<>();
        closed = new HashSet<>();
        visited.add(new State(initialState, 0));
        long end;

        while(!visited.isEmpty()) {
            State currentNode = visited.remove();
            max = currentNode.depth;
            if (isSolved(currentNode, solutionState)) {
                solutionPath = Path.getSolutionPath(currentNode, closed);
                //System.out.println(solutionPath);
                max = currentNode.depth;
                end = System.nanoTime();
                data = new Data(visited.size(), closed.size(), max, end - start, solutionPath);
                solutionPath = "";
                return currentNode;
            }
            closed.add(currentNode);
            for (State n : currentNode.generateNeighbours(order)) {
                if (!closed.contains(n)) {
                    int f = metric.estimateDistanceToSolutionState(n, solutionState);
                    if (!visited.contains(n))
                        visited.add(new State(n, f));
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
