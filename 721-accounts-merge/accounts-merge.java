import java.util.*;

class Solution {

    int[] parent;

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) parent[pa] = pb;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        int id = 0;

        for (List<String> acc : accounts) {
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, id++);
                }
                emailToName.put(email, acc.get(0));
            }
        }

        parent = new int[id];
        for (int i = 0; i < id; i++) parent[i] = i;

        for (List<String> acc : accounts) {
            int first = emailToId.get(acc.get(1));
            for (int i = 2; i < acc.size(); i++) {
                union(first, emailToId.get(acc.get(i)));
            }
        }

        Map<Integer, List<String>> map = new HashMap<>();

        for (String email : emailToId.keySet()) {
            int root = find(emailToId.get(email));
            map.putIfAbsent(root, new ArrayList<>());
            map.get(root).add(email);
        }

        List<List<String>> res = new ArrayList<>();

        for (int key : map.keySet()) {
            List<String> emails = map.get(key);
            Collections.sort(emails);

            List<String> temp = new ArrayList<>();
            temp.add(emailToName.get(emails.get(0)));
            temp.addAll(emails);

            res.add(temp);
        }

        return res;
    }
}