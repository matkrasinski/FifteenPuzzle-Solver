package com.ai;

import java.util.Arrays;

public class Main {
    public static int[][] goalPuzzle = {{1,2,3,4},
                                        {5,6,7,8},
                                        {9,10,11,12},
                                        {13,14,15,0}};
    public static State goalState = new State(goalPuzzle);
    public static int[][] startPuzzle = {{1,3,4,7},
                                         {5,2,6,0},
                                         {9,10,11,8},
                                         {13,14,15,12}}; // glebokosc 8

    public static State startState = new State(startPuzzle);

    public static char[] order = {'U','R','L','D'};
    public static char[] order2= {'D','L','R','U'};
    public static BFS bfs = new BFS(goalState, startState);
    public static DFS dfs = new DFS(goalState, startState);
    public static AStar aStar = new AStar(goalState, startState);

    public static void main(String[] args) {
        System.out.println("Start : " + Arrays.deepToString(startState.puzzle));

        State foundState = bfs.findSolution(order);
        System.out.println("BFS : " + Arrays.deepToString(foundState.puzzle));
        State foundState1 = bfs.findSolution(order2);
        System.out.println("BFS : " + Arrays.deepToString(foundState1.puzzle));

        State foundState3 = dfs.findSolution(order);
        System.out.println("DFS : " + Arrays.deepToString(foundState3.puzzle));
        State foundState2 = dfs.findSolution(order2);
        System.out.println("DFS : " + Arrays.deepToString(foundState2.puzzle));

        State foundState4 = aStar.findSolution(order);
        System.out.println("Astar : " + Arrays.deepToString(foundState4.puzzle));
        State foundState5 = aStar.findSolution(order2);
        System.out.println("Astar : " + Arrays.deepToString(foundState5.puzzle));
    }
}
