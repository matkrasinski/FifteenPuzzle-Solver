package com.ai;

import java.awt.*;

public class Main {
    public static int[][] goalPuzzle = {{1,2,3,4},
                                        {5,6,7,8},
                                        {9,10,11,12},
                                        {13,14,15,0}};
    public static State goalState = new State(goalPuzzle,4,4, new Point(3,3));
    public static int[][] startPuzzle = {{1,2,3,4},
                                         {5,6,7,8},
                                         {9,10,11,12},
                                         {13,14,0,15}};
    public static State startState = new State(startPuzzle,4,4, new Point(3,2));

    public static char[] order = {'d','r','l','u'};
    public static BFS bfs = new BFS(goalState, startState, order);

    public static void main(String[] args) {
        System.out.println(bfs.FindSolution());

    }
}
