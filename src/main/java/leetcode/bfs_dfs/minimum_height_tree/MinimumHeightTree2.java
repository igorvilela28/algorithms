package leetcode.bfs_dfs.minimum_height_tree;

import java.util.*;

public class MinimumHeightTree2 {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> result = new ArrayList<>();

        // Minimum Height Trees can have maximum one or 2 roots node
        if (n <= 2) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
            return result;
        }

        HashMap<Integer, List<Integer>> adjList = new HashMap<>();

        int[] degree = new int[n];

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            List<Integer> neighborsA = adjList.getOrDefault(a, new ArrayList<>());
            neighborsA.add(b);
            adjList.put(a, neighborsA);
            degree[a]++;

            List<Integer> neighborsB = adjList.getOrDefault(b, new ArrayList<>());
            neighborsB.add(a);
            adjList.put(b, neighborsB);
            degree[b]++;
        }


        result = findMinHeightNodes(adjList, degree);
        return result;
    }

    //

    private List<Integer> findMinHeightNodes(
            Map<Integer, List<Integer>> adjList,
            int[] degree
    ) {

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                queue.addLast(i);
            }
        }

        int remainingNodes = adjList.size();

        while (remainingNodes > 2) {

            int layerSize = queue.size();
            remainingNodes -= layerSize;


            // Como  processa camadas inteiras antes de testar a parada,
            // a fila nunca mistura nós de camadas diferentes.
            for (int i = 0; i < layerSize; i++) {

                int node = queue.removeFirst();

                List<Integer> neighbors = adjList.getOrDefault(node, new ArrayList<>());
                for (Integer neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.addLast(neighbor);
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.removeFirst());
        }
        return result;
    }

    public static void main(String[] args) {

        //Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
        //Output: [3,4]

        int[][] edges = new int[][]{
                {3, 0},
                {3, 1},
                {3, 2},
                {3, 4},
                {5, 4},
        };

        MinimumHeightTree2 minimumHeightTree = new MinimumHeightTree2();
        //minimumHeightTree.findMinHeightTrees(6, edges);

        int[][] edges2 = new int[][]{
                {0, 1},
                {1, 2},
                {1, 3},
                {2, 4},
                {3, 5}
        };

        //minimumHeightTree.findMinHeightTrees(6, edges2);

        int[][] edges3 = new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}
        };

        minimumHeightTree.findMinHeightTrees(5, edges3);

        //   2


    }

}
