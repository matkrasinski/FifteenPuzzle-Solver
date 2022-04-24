package com.ai;

import com.sun.security.jgss.GSSUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class State {

    public State prevState;
    public List<State> neighbours;
    public int[][] puzzle;
    public char step;
    public String solution;
    public int width, height;
    public Point zeroIndex;
    public int depth = 0;


    public State(int[][] puzzle,int puzzleWidth, int puzzleHeight, Point zeroIndex) {
        this.neighbours = new ArrayList<>();
        this.width = puzzleWidth;
        this.height = puzzleHeight;
        this.puzzle = puzzle;
        this.zeroIndex = zeroIndex;
        this.solution = "";
    }
    public State(State state) {
        this.neighbours = new ArrayList<>();
        this.puzzle = state.puzzle;
        this.prevState = state.prevState;
        this.zeroIndex = state.zeroIndex;
        this.solution = state.solution;
    }
    public State move(char direction) {
        State newState = new State(this);
        newState.step = direction;
        newState.solution += direction;
        newState.prevState = this;
        newState.depth = depth + 1;
        switch (direction) {
            case 'u' -> newState.zeroIndex.x--;
            case 'd' -> newState.zeroIndex.x++;
            case 'l' -> newState.zeroIndex.y--;
            case 'r' -> newState.zeroIndex.y++;
            default -> {
            }
        }
        swap(newState.puzzle, zeroIndex.x, zeroIndex.y, zeroIndex.x, zeroIndex.y);
//        System.out.println(zeroIndex.x + " " + zeroIndex.y);
        return newState;
    }
    public boolean isMovePossible(char direction) {

        if (direction == 'd' && zeroIndex.x == 3) {
            return false;
        }
        if (direction == 'u' && zeroIndex.x == 0) {
            return false;
        }
        if (direction == 'r' && zeroIndex.y == 3) {
            return false;
        }
        return direction != 'l' || zeroIndex.y != 0;

    }
    public static String charToString(char c) {
        return String.valueOf(c);
    }

    public void print() {
        for(int i = 0; i < height;i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(puzzle[i][j] +  " ");
            }
            System.out.println();
        }
    }

    public boolean isSolution()
    {
        int correctValue = 1;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (i == this.width - 1 && j == this.height - 1) {
                    break;
                }
                if (puzzle[i][j] != correctValue++) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPuzzleSame(State other) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if(this.puzzle[i][j] != other.puzzle[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void generateNeighbours(char[] order) {
        for(char direction : order) {
            if(isMovePossible(direction) && isMovePossible(direction)) {
                neighbours.add(move(direction));
            }
        }
    }
    private boolean isNotGoingBack(char direction) {
        return (this.step != 'l' || direction != 'r') &&
                (this.step != 'u' || direction != 'd') &&
                (this.step != 'r' || direction != 'l') &&
                (this.step != 'd' || direction != 'u');
    }

    public static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int tmp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = tmp;
    }
}
