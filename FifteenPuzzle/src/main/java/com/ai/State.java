package com.ai;

import java.awt.*;
import java.util.*;
import java.util.List;

public class State implements Comparable<State> {

    public State prevState;
    public int[][] puzzle;
    public char step;
    public int width, height;
    public Point zeroIndex;
    public int depth;
    public boolean havePrev;
    public int prevId;
    public int id;
    public int priority;

    public State(int[][] puzzle){
        this.puzzle = puzzle;
        this.width = puzzle[0].length;
        this.height = puzzle.length;
        this.havePrev = false;
        this.depth = 0;
        this.id = hashCode();
        this.zeroIndex = findZero();
    }
    public State(State state, int priority) {
        this.puzzle = state.puzzle;
        this.prevState = state.prevState;
        this.zeroIndex = state.zeroIndex;
        this.depth = state.depth;
        this.id = state.id;
        this.prevId = state.prevId;
        this.width = state.width;
        this.height = state.height;
        this.step = state.step;
        this.priority = priority;
        this.havePrev = state.havePrev;

    }
    public State(char direction, int[][] puzzle, int prevID, int depth) {
        this.width = puzzle[0].length;
        this.height = puzzle.length;
        this.puzzle = puzzle;
        this.step = direction;
        this.zeroIndex = findZero();
        this.havePrev = true;
        this.id = hashCode();
        this.prevId = prevID;
        this.depth = depth + 1;
    }
    public void print() {
        for(int i = 0; i < height;i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(puzzle[i][j] +  " ");
            }
            System.out.println();
        }
    }
    public List<State> generateNeighbours(char[] order) {
        List<State> neighbours = new ArrayList<>();
        for (char step : order) {
            if(canMove(step)) {
                neighbours.add(new State(step, move(step), id, depth));
            }
        }
        return neighbours;
    }
    public boolean canMove(char direction) {
        return ((zeroIndex.y != 0 && direction == 'U') ||
                (zeroIndex.y != puzzle.length - 1 && direction == 'D') ||
                (zeroIndex.x != 0 && direction == 'L') ||
                (zeroIndex.x != puzzle.length - 1) && direction == 'R') && isNotGoingBack(direction);
    }
    public int[][] move(char direction) {
        int[][] childPuzzle = new int[puzzle.length][puzzle[0].length];

        for (int i = 0; i < puzzle.length; i++) {
            System.arraycopy(puzzle[i], 0, childPuzzle[i], 0, puzzle.length);
        }

        switch (direction) {
            case 'U' ->
                swap(childPuzzle, zeroIndex.y,zeroIndex.x, zeroIndex.y - 1,zeroIndex.x);
            case 'D' ->
                swap(childPuzzle, zeroIndex.y,zeroIndex.x, zeroIndex.y + 1,zeroIndex.x);
            case 'R' ->
                swap(childPuzzle, zeroIndex.y,zeroIndex.x, zeroIndex.y,zeroIndex.x + 1);
            case 'L' ->
                swap(childPuzzle, zeroIndex.y,zeroIndex.x, zeroIndex.y,zeroIndex.x - 1);
            default -> {
            }
        }
        return childPuzzle;
    }
    public static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int tmp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = tmp;
    }
    public Point findZero() {
        int x = 0, y = 0;
        for(int i=0 ; i< puzzle.length ; i ++){
            for (int j = 0; j < puzzle.length; j++) {
                if(puzzle[i][j] == 0){
                    x = j;
                    y = i;
                    break;
                }
            }
        }
        return new Point(x, y);
    }
    private boolean isNotGoingBack(char direction) {
        return (this.step != 'L' || direction != 'R') &&
                (this.step != 'U' || direction != 'D') &&
                (this.step != 'R' || direction != 'L') &&
                (this.step != 'D' || direction != 'U');
    }
    public void setPriority(int newValue) {
        this.priority = newValue;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.priority, o.priority);
    }
}
