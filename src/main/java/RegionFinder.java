/*
Find length of the largest region in Boolean Matrix
Consider a matrix with rows and columns, where each cell contains either a ‘0’ or a ‘1’ and any cell containing a 1 is
called a filled cell. Two cells are said to be connected if they are adjacent to each other horizontally, vertically,
or diagonally .If one or more filled cells are also connected, they form a region. find the length of the largest region.

Examples:

Input : M[][5] = { 0 0 1 1 0
                   1 0 1 1 0
                   0 1 0 0 0
                   0 0 0 0 1 }
Output : 6
Ex: in the following example, there are 2 regions one with length 1 and the other as 6.
    so largest region : 6
 */

package main.java;

public class RegionFinder {

    public static void main(String[] args) {
        boolean[][] grid = {
                {false, false, true, true, false},
                {true, false, true, true, false},
                {false, true, false, false, false},
                {false, false, false, false, true}};

        System.out.println(sizeOfLargestRegion(grid));
    }

    private static int[] rowNbr = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] colNbr = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static boolean[][] visited;

    public static int sizeOfLargestRegion(boolean[][] map) {
        int row = map.length;
        int col = map[0].length;
        int maxCount = Integer.MIN_VALUE;
        visited = new boolean[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (!visited[i][j] && map[i][j]) {
                    int count = DFS(map, i, j, row, col, 1);
                    maxCount = Math.max(maxCount, count);
                }
            }
        }
        return maxCount;
    }

    private static int DFS(boolean[][] map, int i, int j, int row, int col, int count) {
        visited[i][j] = true;
        for (int k=0; k<8; k++) {
            if (isSafe(map, i+rowNbr[k], j+colNbr[k], row, col)){
                count = DFS(map, i+rowNbr[k], j+colNbr[k], row, col, ++count);
            }
        }

        return count;
    }

    private static boolean isSafe(boolean[][] map, int i, int j, int row, int col) {
        return i >= 0 && i <row && j >= 0 && j<col && map[i][j] && !visited[i][j];
    }
}
