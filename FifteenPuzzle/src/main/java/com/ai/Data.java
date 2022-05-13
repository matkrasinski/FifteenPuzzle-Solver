package com.ai;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Data {

    private int solutionLength;
    private int visitedStates;
    private int closedStates;
    private int maxDepth;
    private double time;

    public Data(int solutionLength, int visitedStates, int closedStates, int maxDepth, double time) {
        this.solutionLength = solutionLength;
        this.visitedStates = visitedStates;
        this.closedStates = closedStates;
        this.maxDepth = maxDepth;
        this.time = BigDecimal.valueOf(time/1000.0)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
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

    @Override
    public String toString() {
        return "solutionLength=" + solutionLength +
                ", visitedStates=" + visitedStates +
                ", closedStates=" + closedStates +
                ", maxDepth=" + maxDepth +
                ", time=" + time + "ms";
    }
}
