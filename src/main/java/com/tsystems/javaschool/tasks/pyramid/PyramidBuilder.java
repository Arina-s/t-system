package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        if (inputNumbers.contains(null) || !checkAmountNumbers(inputNumbers.size())) {
            throw new CannotBuildPyramidException();
        }
        Collections.sort(inputNumbers);
        int rows = countRows(inputNumbers.size());
        int columns = countColumns(rows);
        int[][] array = new int[rows][columns];
        array = fillPyramid(array, inputNumbers);
        return array;
    }

    private boolean checkAmountNumbers(int size) {
        if (size > Integer.MAX_VALUE - 3) {
            return false;
        }
        int possibleSize = 1;
        int temp = 2;
        do {
            if (possibleSize == size) {
                return true;
            }
            possibleSize += temp;
            temp++;
        } while (possibleSize <= size);
        return false;
    }

    private int countRows(int size) {
        int count = 0;
        int temp = 1;
        while (size > 0) {
            count++;
            size -= temp;
            temp++;
        }
        return count;
    }

    private int countColumns(int rows) {
        int count = 1;
        int tempRows = 1;
        while (tempRows < rows) {
            tempRows++;
            count += 2;
        }
        return count;
    }

    private int[][] fillPyramid(int[][] array, List<Integer> inputNumbers) {
        int rows = array.length;
        int column = array[0].length;
        int indexColumn = column;
        int countNumbersInRow = 1;
        int index = 0;
        indexColumn /= 2;
        for (int i = 0; i < rows; i++) {
            int distance = 0;
            for (int j = 0; j < countNumbersInRow; j++) {
                array[i][indexColumn + distance] = inputNumbers.get(index);
                index++;
                distance += 2;
            }
            countNumbersInRow++;
            indexColumn--;
        }
        return array;
    }

}
