package com.lyf.code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 */




public class Solution0108 {
    public static void setZeroes(int[][] matrix) {
        Set<Integer> zeroRowIndex = new HashSet<>();
        Set<Integer> zeroColumnIndex = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRowIndex.add(i);
                    zeroColumnIndex.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (zeroRowIndex.contains(i) || zeroColumnIndex.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void setZeroes2(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        boolean[] rn =new boolean[r];
        boolean[] cn =new boolean[c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    rn[i] = true;
                    cn[j] = true;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rn[i] || cn[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void setZeroes3(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;

        boolean rZero = false;
        boolean cZero = false;
        for (int i = 0; i < r; i++) {
            if (matrix[i][0] == 0) {
                cZero = true;
                break;
            }
        }
        for (int j = 0; j < c; j++) {
            if (matrix[0][j] == 0){
                rZero = true;
                break;
            }
        }

        if (r > 1 && c>1) {
            for (int i = 1; i < r; i++) {
                for (int j = 1; j < c; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = matrix[0][j] = 0;
                    }
                }
            }
            for (int i = 1; i < r; i++) {
                for (int j = 1; j < c; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        if (cZero) {
            for (int i = 0; i < r; i++) {
                matrix[i][0] = 0;
            }
        }

        if (rZero) {
            for (int j = 0; j < c; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
//        int[][] matrix={{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        int[][] matrix={{1,0,3}};
        setZeroes3(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
