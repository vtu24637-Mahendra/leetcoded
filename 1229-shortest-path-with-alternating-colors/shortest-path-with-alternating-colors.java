import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<List<Integer>> red = new ArrayList<>();
        List<List<Integer>> blue = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            red.add(new ArrayList<>());
            blue.add(new ArrayList<>());
        }

        for (int[] e : redEdges) red.get(e[0]).add(e[1]);
        for (int[] e : blueEdges) blue.get(e[0]).add(e[1]);

        int[][] dist = new int[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});

        dist[0][0] = 0;
        dist[0][1] = 0;

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int color = cur[1];

            if (color == 0) {
                for (int nei : red.get(node)) {
                    if (dist[nei][1] == Integer.MAX_VALUE) {
                        dist[nei][1] = dist[node][0] + 1;
                        q.offer(new int[]{nei, 1});
                        if (ans[nei] == -1 || dist[nei][1] < ans[nei]) {
                            ans[nei] = dist[nei][1];
                        }
                    }
                }
            } else {
                for (int nei : blue.get(node)) {
                    if (dist[nei][0] == Integer.MAX_VALUE) {
                        dist[nei][0] = dist[node][1] + 1;
                        q.offer(new int[]{nei, 0});
                        if (ans[nei] == -1 || dist[nei][0] < ans[nei]) {
                            ans[nei] = dist[nei][0];
                        }
                    }
                }
            }
        }

        return ans;
    }
}