import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] previous = new int[n];

        Stack<int[]> st = new Stack<>();
        st.push(new int[]{0, 0});

        while (!st.isEmpty()) {
            int[] cur = st.pop();
            int node = cur[0];
            int state = cur[1];

            int ch = s.charAt(node) - 'a';

            if (state == 0) {
                previous[node] = last[ch];

                if (last[ch] != -1) {
                    newParent[node] = last[ch];
                }

                last[ch] = node;

                st.push(new int[]{node, 1});

                for (int child : tree[node]) {
                    st.push(new int[]{child, 0});
                }

            } else {
                last[ch] = previous[node];
            }
        }

        List<Integer>[] modified = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            modified[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            modified[newParent[i]].add(i);
        }

        List<Integer> order = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            order.add(node);

            for (int child : modified[node]) {
                stack.push(child);
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = order.size() - 1; i > 0; i--) {
            int node = order.get(i);
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}