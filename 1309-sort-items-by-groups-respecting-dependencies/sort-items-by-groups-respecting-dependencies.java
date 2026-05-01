import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) group[i] = m++;
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        if (itemOrder.size() == 0 || groupOrder.size() == 0) return new int[0];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int g : groupOrder) map.put(g, new ArrayList<>());

        for (int item : itemOrder) {
            map.get(group[item]).add(item);
        }

        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder) {
            result.addAll(map.get(g));
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) ans[i] = result.get(i);

        return ans;
    }

    List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        List<Integer> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);

            for (int nei : graph.get(cur)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return res.size() == size ? res : new ArrayList<>();
    }
}