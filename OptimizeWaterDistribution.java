// Time Complexity : O{(n+m) * log (n+m)}
// Space Complexity :O(n+m)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : how to add node cost to mst --> build a self edge and use it as the source

import java.util.*;

// LC 1168
public class OptimizeWaterDistribution {

    /**
     * Intuition:
     * Create a connected component with min. cost == build MST
     * <p>
     * Now, how to add edge weight for a well --> use self directed edge such that edge == (node, node, well[node])
     * <p>
     * Apply Prim's algo or Kruskal's Algo to build a MST.
     *
     * @param n
     * @param wells
     * @param pipes
     * @return
     */
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        // build the adj list for pipes
        for (int[] pipe : pipes) {
            adjList.get(pipe[0]).add(new int[]{pipe[1], pipe[2]});
            adjList.get(pipe[1]).add(new int[]{pipe[0], pipe[2]});
        }
        // use wells as the source
        // node, parent, cost
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int i = 1; i <= n; i++) {
            pq.offer(new int[]{i, i, wells[i - 1]});
        }
        int sum = 0;
        List<List<Integer>> mst = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int parent = curr[1];
            int cost = curr[2];
            if (visited[node]) {
                continue;
            }
            // poll and mark as visited as this is the min. edge weight possible to connect this node
            visited[node] = true;
            sum += cost;
            if (node == parent) {
                mst.add(List.of(node));
            } else {
                mst.add(Arrays.asList(parent, node));
            }
            for (int[] adj : adjList.get(node)) {
                if (visited[adj[0]]) {
                    continue;
                }
                pq.offer(new int[]{adj[0], node, adj[1]});
            }
        }
        // Print the connection
        for (List<Integer> list : mst) {
            if (list.size() == 1) {
                System.out.print("Well");
            } else {
                System.out.print("Pipe");
            }
            System.out.print(list);
        }
        return sum;
    }

}
