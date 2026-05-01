import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], -1);
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] d : dir) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];

                if (x >= 0 && y >= 0 && x < m && y < n && dist[x][y] == -1) {
                    dist[x][y] = dist[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{x, y});
                }
            }
        }

        return dist;
    }
}