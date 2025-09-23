package leetcode.bfs_dfs.all_paths_from_source_to_target;

import java.util.*;

public class AllPathsFromSourceToTarget {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        int source = 0;
        int target = graph.length - 1;

        List<List<Integer>> paths = new ArrayList<>();

        dfs(graph, source, target, paths, new ArrayList<>());

        return paths;
    }

    private void dfs(int[][] graph,
                     int node,
                     int target,
                     List<List<Integer>> paths,
                     List<Integer> currentPath
    ) {

        if (node == target) {
            List<Integer> clone = new ArrayList<>(currentPath);
            clone.add(node);
            paths.add(clone);
            return;
        }

        currentPath.add(node);
        int[] neighbors = graph[node];

        for (int i = 0; i < neighbors.length; i++) {
            dfs(graph, neighbors[i], target, paths, currentPath);
        }

        // remove node as explored from current path
        if (!currentPath.isEmpty()) {
            currentPath.remove(currentPath.size() - 1);
        }
    }

    public static void main(String[] args) {

        // Input: graph = [[1,2],[3],[3],[]]

        AllPathsFromSourceToTarget paths = new AllPathsFromSourceToTarget();

        int[][] graph = new int[][]{
                {1, 2},
                {3},
                {3},
                {},
        };

        List<List<Integer>> result = paths.allPathsSourceTarget(graph);
        System.out.println(result);

        // Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
        // Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
        int[][] graph2 = new int[][]{
                {4, 3, 1},
                {3, 2, 4},
                {3},
                {4},
                {}
        };

        List<List<Integer>> result2 = paths.allPathsSourceTarget(graph2);
        System.out.println(result2);
    }
}
