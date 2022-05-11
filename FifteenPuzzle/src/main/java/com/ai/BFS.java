package com.ai;

import java.util.*;

public class BFS {

    public State solutionState;
    public State initialState;
    public String solutionPath;
    public char[] neighborhoodSearchOrder;
    public List<State> visitedStates;
    public boolean result;


    public BFS(State goalState, State initState, char[] searchOrder) {
        this.solutionState = goalState;
        this.initialState = initState;
        this.neighborhoodSearchOrder = searchOrder;
    }

    public State findSolution(char[] order) {
        if (Arrays.deepEquals(initialState.puzzle, solutionState.puzzle)) {
            return initialState;
        }
        int depth = 0;
        List<State> neighbours;
        visitedStates = new ArrayList<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(initialState);
        result = false;
        while (!queue.isEmpty()) {
            State currentNode = queue.remove();
            if (Arrays.deepEquals(currentNode.puzzle, solutionState.puzzle)) {
                result = true;
                getSolutionPath(currentNode);
                System.out.println(solutionPath);
                return currentNode;
            }
            if (!visitedStates.contains(currentNode)) {
                visitedStates.add(currentNode);
            }
            neighbours = currentNode.generateNeighbours(order);
            for (State n : neighbours) {
                if (!visitedStates.contains(n)) {
                    queue.add(n);
                }
            }
        }

        System.out.println(solutionPath);
        return null;
    }
    public void getSolutionPath(State actual) {
        while (actual.havePrev) {
            solutionPath += actual.step;
            for (State n : visitedStates) {
                if (actual.prevId == n.id) {
                    actual = n;
                    break;
                }
            }
        }
    }
}
