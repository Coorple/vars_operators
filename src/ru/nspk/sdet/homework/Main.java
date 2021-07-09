package ru.nspk.sdet.homework;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final Random r = new Random();
    private static int column = 0;
    private static int row = 0;
    private static final int[][] mountains = new int[7][2];
    private static final int MAP_SIZE = 10;
    public static void main(String[] args) {
        instr();
        for (int[] mount: mountains) {
            do {
                mount[0] = r.nextInt(10);
                mount[1] = r.nextInt(10);
            } while (mount[0] == 0 && mount[1] == 0);
        }
	    while (true) {
            draw();
            String[] command = next();
            if (change(command)) {
                break;
            }
        }
    }

    public static void draw() {
        for (var i = 0; i < MAP_SIZE; i++) {
            System.out.println();

            for (var j = 0; j < MAP_SIZE; j++) {
                if (mountIter(i, j)) {
                    continue;
                }
                System.out.print(row == i && column == j ? "0 " : "X ");

            }
        }
    }

    public static boolean mountIter(int i, int j) {
        for (int[] mount: mountains) {
            if (mount[0] == i && mount[1] == j) {
                System.out.print("T ");
                return true;
            }
        }
        return false;
    }

    public static String[] next() {
        System.out.println();
        return scanner.nextLine().split(" ");
    }

    public static boolean checkMount(int row, int column) {
        for (int[] mount: mountains) {
            if (mount[0] == row && mount[1] == column) {
                System.out.println("Mountain on the road! u cant do it!");
                return true;
            }
        }
        return false;
    }

    public static boolean change(String[] argus) {
        for (String arg : argus) {
            int tempRow = row;
            int tempColumn = column;
            if ("D".equals(arg)) {
                tempRow = row + 1;
            } else if ("U".equals(arg)) {
                tempRow = row - 1;
            } else if ("R".equals(arg)) {
                tempColumn = column + 1;
            } else if ("L".equals(arg)) {
                tempColumn = column - 1;
            } else if ("exit".equals(arg)) {
                return true;
            }
            if (checkMount(tempRow, tempColumn)) {
                break;
            } else {
                row = tempRow;
                column = tempColumn;
            }
            if (row > MAP_SIZE - 1) {
                row -= MAP_SIZE;
            }
            if (row < 0) {
                row += MAP_SIZE;
            }
            if (column > MAP_SIZE - 1) {
                column -= MAP_SIZE;
            }
            if (column < 0) {
                column += MAP_SIZE;
            }
        }
        return false;
    }

    public static void instr() {
        System.out.println("""
                
                Welcome to this simple game ;)
                rules are simple - you start at position 0: 0
                if you want go right - print to console 'R'
                left - 'L'
                up - 'U'
                down - 'D'
                You can print several commands in one line - for example,
                if u print 'R R R' - u will move 3 positions to the right
                if u print wrong command - for example, R R K D D - K would be ignored
                and if u wanna exit - just type 'exit'
                BEWARE OF THE MOUNTAINS! (which is 'T'). u cant go through mountain!
                u can change size of map - just change var 'mapSize'
                same goes with mountains - just change size of first array in mountains array
                ENJOY!
                """);
    }
}
