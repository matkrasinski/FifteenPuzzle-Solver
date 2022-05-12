package com.ai;

import java.util.*;

public class AStar {
    public State solutionState;
    public State initialState;
    public String solutionPath = "";
    public Queue<State> visited;
    public Set<State> closed;

    public AStar(State solutionState, State initialState) {
        this.solutionState = solutionState;
        this.initialState = initialState;
    }

    public State findSolution(char[] order) {
        if (Arrays.deepEquals(initialState.puzzle, solutionState.puzzle)) {
            return initialState;
        }
        visited = new PriorityQueue<>();
        closed = new HashSet<>();
        visited.add(new State(initialState, 0));
        while(!visited.isEmpty()) {
            State currentNode = visited.remove();
            if (Arrays.deepEquals(currentNode.puzzle, solutionState.puzzle)) {
                getSolutionPath(currentNode);
                System.out.println(solutionPath);
                solutionPath = "";
                return currentNode;
            }
            closed.add(currentNode);
            for (State n : currentNode.generateNeighbours(order)) {
                if (!closed.contains(n)) {
                    int f = calculatePriority(n);
                    if (!visited.contains(n))
                        visited.add(new State(n, f));
                    else
                        if (n.priority > f)
                            update(n, f);
                }
            }
        }

        return null;
    }
    public int calculatePriority(State current) {
        return calculateHamming(current) + current.depth;
    }
    public int calculateHamming(State current) {
        int result = 0;
        for (int i = 0; i < solutionState.width; i++)
            for (int j = 0; j < solutionState.height; j++)
                if (current.puzzle[i][j] != solutionState.puzzle[i][j])
                    if (current.puzzle[i][j] != 0)
                        result++;
        return result;
    }
    public int calculateManhattan(State current) {
        int result = 0;
        for (int i = 0; i < current.puzzle.length; i++)
            for (int j = 0; j < current.puzzle[0].length; j++)
                if (current.puzzle[i][j] != solutionState.puzzle[i][j])
                    for (int i2 = 0; i2 < current.puzzle.length; i2++)
                        for (int j2 = 0; j2 < current.puzzle[0].length; j2++) {
                            if (current.puzzle[i][j] == solutionState.puzzle[i2][j2]) {
                                result += Math.abs(i2 - i) + Math.abs(j2 - j);
                                break;
                            }
                        }
        return result;
    }

    public void update(State n, int f) {
        for (State i : visited)
            if (i.equals(n))
                n.setPriority(f);
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
