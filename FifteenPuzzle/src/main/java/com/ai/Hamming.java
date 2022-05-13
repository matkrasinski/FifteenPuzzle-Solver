package com.ai;

public class Hamming extends Metric{

    @Override
    public int estimateDistanceToSolutionState(State current, State solutionState) {
        int result = 0;
        for (int i = 0; i < solutionState.width; i++)
            for (int j = 0; j < solutionState.height; j++)
                if (current.puzzle[i][j] != solutionState.puzzle[i][j])
                    if (current.puzzle[i][j] != 0)
                        result++;
        return result + current.depth;
    }
}
