package leetcode.bfs_dfs.minimum_height_tree;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-height-trees/
 *
 * Nesse exercicio, usei uma abordagem mais simples de calcular a altura de uma árvore.
 * Porém, fica ineficiente para Ns grandes, visto que estou calculando a altura considerando cada nó
 * como root.
 *
 * Ver o segundo exemplo para uma abordagem mais eficiente.
 */
public class MinimumHeightTree {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        HashMap<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            List<Integer> neighborsA =  adjList.getOrDefault(a, new ArrayList<>());
            neighborsA.add(b);
            adjList.put(a, neighborsA);

            List<Integer> neighborsB =  adjList.getOrDefault(b, new ArrayList<>());
            neighborsB.add(a);
            adjList.put(b, neighborsB);
        }

        int minHeight = Integer.MAX_VALUE;
        int[][] heights = new int[n][1];

        for (int i = 0; i < n; i++) {
            int height = findHeight(adjList, i);
            if (height < minHeight) {
                minHeight = height;
            }
            heights[i][0] = height;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < heights.length; i++) {
            if (heights[i][0] == minHeight) {
                result.add(i);
            }
        }
        return result;
    }

    private int findHeight(Map<Integer, List<Integer>> adjList, int root) {

        int height = 0;
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(root);

        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int node = queue.removeFirst();
                visited.add(node);
                List<Integer> neighbors = adjList.getOrDefault(node, new ArrayList<>());
                for (Integer neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.addLast(neighbor);
                    }
                }
            }
            height++;
        }

        return height;
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

        MinimumHeightTree minimumHeightTree = new MinimumHeightTree();
        minimumHeightTree.findMinHeightTrees(6, edges);
    }

}
