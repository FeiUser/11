package com.lyf.code;

import java.util.Arrays;

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 */
public class Solution0107 {

    public static void rotate(int[][] matrix) {
        int temp;
        // duichen
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i < j) {
                    temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        //zuoyou
        for (int i = 0; i < matrix.length; i++) {
            int lastIndex = matrix.length - 1;
            for (int j = 0; j < lastIndex; j++, lastIndex--) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][lastIndex];
                matrix[i][lastIndex] = temp;
            }
        }
    }

    public static void rotate2(int[][] matrix) {
        int temp;
        // 斜对称
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i < j) {
                    temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        // 左右
        for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
            for (int k = 0; k < matrix.length; k++) {
                temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }



    }

    public static void main(String[] args) {
        int[][] matrix={{1,2,3},{4,5,6},{7,8,9}};
        rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
