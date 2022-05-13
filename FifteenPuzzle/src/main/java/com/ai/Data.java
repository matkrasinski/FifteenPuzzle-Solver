package com.ai;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Data {

    private final int solutionLength;
    private int visitedStates;
    private int closedStates;
    private int maxDepth;
    private double time;
    private String solutionPath = "";

    public Data( int visitedStates, int closedStates, int maxDepth, double time, String solutionPath) {
        this.solutionLength = solutionPath.length();
        this.visitedStates = visitedStates;
        this.closedStates = closedStates;
        this.maxDepth = maxDepth;
        this.time = BigDecimal.valueOf(time/1e+6).doubleValue();


        this.solutionPath = solutionPath;
    }
    public Data(int solutionLength) {
        this.solutionLength = solutionLength;
    }

    public int getSolutionLength() {
        return solutionLength;
    }

    public int getVisitedStates() {
        return visitedStates;
    }

    public int getClosedStates() {
        return closedStates;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public double getTime() {
        return time;
    }
    public String getSolution() {
        return solutionPath;
    }
    @Override
    public String toString() {
        return "solutionLength=" + solutionLength +
                ", visitedStates=" + visitedStates +
                ", closedStates=" + closedStates +
                ", maxDepth=" + maxDepth +
                ", time=" + time + "ms";
    }
}
