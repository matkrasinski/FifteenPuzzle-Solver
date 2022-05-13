package com.ai;

import java.util.Arrays;

public class Main {
    public static int[][] goalPuzzle = {{1,2,3,4},
                                        {5,6,7,8},
                                        {9,10,11,12},
                                        {13,14,15,0}};
    public static State goalState = new State(goalPuzzle);
    public static int[][] startPuzzle = {{1,2,4,7},
                                         {5,6,0,3},
                                         {9,10,11,8},
                                         {13,14,15,12}}; // glebokosc 7

    public static State startState = new State(startPuzzle);

    public static char[] order = {'U','R','L','D'};
    public static char[] order2= {'D','L','R','U'};
    public static Strategy bfs = new BFS(goalState, startState);
    public static Strategy dfs = new DFS(goalState, startState);
    public static Strategy aStarHam = new AStar(goalState, startState, "hamm");
    public static Strategy aStarMan = new AStar(goalState, startState, "manh");

    public static void main(String[] args) {
        System.out.println("Start : " + Arrays.deepToString(startState.puzzle));

        State foundState = bfs.findSolution(order);
        System.out.println("BFS : " + Arrays.deepToString(foundState.puzzle));
        System.out.println(bfs.data);
        State foundState1 = bfs.findSolution(order2);
        System.out.println("BFS : " + Arrays.deepToString(foundState1.puzzle));
        System.out.println(bfs.data + "\n");

        State foundState3 = dfs.findSolution(order);
        System.out.println("DFS : " + Arrays.deepToString(foundState3.puzzle));
        System.out.println("dfs " + dfs.data);
        State foundState2 = dfs.findSolution(order2);
        System.out.println("DFS : " + Arrays.deepToString(foundState2.puzzle));
        System.out.println(dfs.data + "\n");

        State foundState4 = aStarMan.findSolution(order);
        System.out.println("Astar : " + Arrays.deepToString(foundState4.puzzle));
        System.out.println(aStarMan.data);
        State foundState5 = aStarMan.findSolution(order2);
        System.out.println("Astar : " + Arrays.deepToString(foundState5.puzzle));
        System.out.println(aStarMan.data + "\n");

        State foundState6 = aStarHam.findSolution(order);
        System.out.println("Astar : " + Arrays.deepToString(foundState6.puzzle));
        System.out.println(aStarHam.data);
        State foundState7 = aStarHam.findSolution(order2);
        System.out.println("Astar : " + Arrays.deepToString(foundState7.puzzle));
        System.out.println(aStarHam.data + "\n");


    }
}
