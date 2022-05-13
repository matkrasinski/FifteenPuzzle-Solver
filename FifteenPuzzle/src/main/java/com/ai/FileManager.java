package com.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    public static State initialState(String initialName)
    {
        int [][] puzzle = null;
        int width;
        int height;
        try
        {
            Scanner scanner = new Scanner(new File(initialName));
            width = Integer.parseInt(scanner.next());
            height = Integer.parseInt(scanner.next());
            puzzle = new int [width][height];
            for (int i=0; i<puzzle.length; i++)
                for (int j = 0; j < puzzle.length; j++)
                    puzzle[i][j] = Integer.parseInt(scanner.next());
            scanner.close();
        }
        catch(Exception e) { e.printStackTrace(); }

        assert puzzle != null;
        return new State(puzzle);
    }
    public static void saveSolutionToFile(Data infos, String fileName) {
        try{
            PrintWriter out = new PrintWriter (fileName);
            out.print(exportInfo(infos));
            out.close();

        } catch( IOException e) {
            e.printStackTrace();
        }

    }
    public static String exportInfo(Data infos)
    {
        StringBuilder ret = new StringBuilder();
        if (!infos.getSolution().isEmpty()){
            ret.append(infos.getSolutionLength()).append("\n")
                    .append(infos.getSolution());
        } else {
            ret.append(infos.getSolutionLength());
        }


        return ret.toString();
    }
    public static void saveStatsToFile(Data infos, String fileName) {
        try{
            PrintWriter out = new PrintWriter (fileName);
            out.print(exportStats(infos));
            out.close();

        } catch( IOException e) {
            e.printStackTrace();
        }

    }
    public static String exportStats(Data infos)
    {
        StringBuilder ret = new StringBuilder();

        ret.append(infos.getSolutionLength()).append("\n")
                .append(infos.getVisitedStates()).append("\n")
                .append(infos.getClosedStates()).append("\n")
                .append(infos.getMaxDepth()).append("\n")
                .append(String.format("%.3f", infos.getTime()));



        return ret.toString();
    }

}
