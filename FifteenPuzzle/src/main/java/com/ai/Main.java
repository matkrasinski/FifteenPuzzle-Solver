package com.ai;

import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static int[][] goalPuzzle = {{1,2,3,4},
                                        {5,6,7,8},
                                        {9,10,11,12},
                                        {13,14,15,0}};
    public static State goalState = new State(goalPuzzle);
    public static void main(String[] args) {
        Strategy strategy = null;
        String strategyName = args[0];
        String metric = args[1];
        String initialInputName = args[2];
        String initialOutputName = args[3];
        String initialStatName = args[4];
        char[] order = createOrder(metric);

        State state = FileManager.initialState(initialInputName);

        if (Objects.equals(strategyName, "astr")) {
            strategy = new AStar(goalState, state, metric);
            order = new char[]{'R', 'D', 'U', 'L'};
        } else if (Objects.equals(strategyName, "bfs")) {
            strategy = new BFS(goalState, state);
        } else if (Objects.equals(strategyName, "dfs")) {
            strategy = new DFS(goalState, state);
        }

        assert strategy != null;
        strategy.findSolution(order);
        FileManager.saveSolutionToFile(strategy.data, initialOutputName);
        FileManager.saveStatsToFile(strategy.data, initialStatName);
    }

    public static char[] createOrder(String strOrd) {
        return strOrd.toCharArray();
    }

}
