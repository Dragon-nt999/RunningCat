package com.dragonentertainment.runningcat.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomMatrixPositions {
    public static List<int[]> getRandomPositions(int rows, int cols, int count) {
        List<int[]> allPositions = new ArrayList<>();
        List<int[]> result = new ArrayList<>();
        Random random = new Random();

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                allPositions.add(new int[]{x, y});
            }
        }

        Collections.shuffle(allPositions, random);

        for(int i = 0; i < Math.min(count, allPositions.size()); i++) {
            result.add(allPositions.get(i));
        }

        return result;
    }
}
