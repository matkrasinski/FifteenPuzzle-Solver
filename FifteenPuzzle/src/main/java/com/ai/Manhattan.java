package com.ai;

public class Manhattan extends Metric{

    @Override
    public int estimateDistanceToSolutionState(State current, State solutionState) {
        int result = 0;
        for (int i = 0; i < current.puzzle.length; i++)
            for (int j = 0; j < current.puzzle[0].length; j++)
                if (current.puzzle[i][j] != 0 && current.puzzle[i][j] != solutionState.puzzle[i][j])
                    for (int i2 = 0; i2 < current.puzzle.length; i2++)
                        for (int j2 = 0; j2 < current.puzzle[0].length; j2++) {
                            if (current.puzzle[i][j] != 0 && current.puzzle[i][j] == solutionState.puzzle[i2][j2]) {
                                result += Math.abs(i2 - i) + Math.abs(j2 - j);
                                break;
                            }
                        }
        return result + current.depth;
    }

}
