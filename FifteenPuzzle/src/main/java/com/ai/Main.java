package com.ai;

import java.util.Arrays;

public class Main {
    public static int[][] goalPuzzle = {{1,2,3,4},
                                        {5,6,7,8},
                                        {9,10,11,12},
                                        {13,14,15,0}};
    public static State goalState = new State(goalPuzzle);
    public static int[][] startPuzzle = {{5,1,3,4},
                                         {0,2,6,7},
                                         {9,10,11,8},
                                         {13,14,15,12}};
    
    public static State startState = new State(startPuzzle);

    public static char[] order = {'U','R','L','D'};
    public static BFS bfs = new BFS(goalState, startState);
    public static DFS dfs = new DFS(goalState, startState);

    public static void main(String[] args) {
        System.out.println("Start : " + Arrays.deepToString(startState.puzzle));
        State foundState = bfs.findSolution(order);
        System.out.println("BFS : " + Arrays.deepToString(foundState.puzzle));
        State foundState2 = dfs.findSolution(order);
        System.out.println("DFS : " + Arrays.deepToString(foundState2.puzzle));
//        List<State> list = startState.generateChildren(order);
//
//        for (State s : list) {
//            System.out.println(Arrays.deepToString(s.puzzle));
//            System.out.println(s.zeroIndex);
//
//        }



    }
}
