package org.example.test;

import java.nio.ByteBuffer;

public class ByteBufferMatrixMirror {
    public static void main(String[] args) {
        int m = 3;
        int n = 4;

        // 创建一个ByteBuffer用于存储矩阵元素
        ByteBuffer buffer = ByteBuffer.allocate(m * n * Integer.BYTES);

        // 初始化矩阵元素
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                buffer.putInt(i * n + j, i * n + j + 1); // 例如，存储从1到m*n的整数
            }
        }

        // 打印原始矩阵
        System.out.println("Original Matrix:");
        printMatrix(buffer, m, n);

        // 镜像矩阵
        mirrorMatrix(buffer, m, n);

        // 打印镜像后的矩阵
        System.out.println("Mirrored Matrix:");
        printMatrix(buffer, m, n);
    }

    private static void printMatrix(ByteBuffer buffer, int m, int n) {
        // 保存当前的缓冲区位置
        int position = buffer.position();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(buffer.getInt(i * n * Integer.BYTES + j * Integer.BYTES) + " ");
            }
            System.out.println();
        }

        // 恢复缓冲区位置
        buffer.position(position);
    }

    private static void mirrorMatrix(ByteBuffer buffer, int m, int n) {
        // 保存当前的缓冲区位置
        int position = buffer.position();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int leftIndex = i * n * Integer.BYTES + j * Integer.BYTES;
                int rightIndex = i * n * Integer.BYTES + (n - 1 - j) * Integer.BYTES;

                int leftValue = buffer.getInt(leftIndex);
                int rightValue = buffer.getInt(rightIndex);

                buffer.putInt(leftIndex, rightValue);
                buffer.putInt(rightIndex, leftValue);
            }
        }

        // 恢复缓冲区位置
        buffer.position(position);
    }
}

