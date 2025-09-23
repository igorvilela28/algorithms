package leetcode.uber;

import java.util.*;

public class UberAdaSolution2 {

    public static int detonation(int[][] bombs) {

        Map<Integer,List<Integer>> adjList = new HashMap<>();

        // O(n^2)
        for (int i = 0; i < bombs.length; i++) {
            for (int j = 0; j < bombs.length; j++) {
                if (i != j) {
                    boolean trigger = trigger(bombs[i], bombs[j]);
                    if (trigger) {
                        List<Integer> neighbors = adjList.getOrDefault(i, new ArrayList<>());
                        neighbors.add(j);
                        adjList.put(i, neighbors);
                    }
                }
            }
        }

        System.out.println(adjList);

        // no bombs can trigger each other
        if (adjList.isEmpty()) {
            return 1;
        }

        int detonations = maxDetonation(adjList);

        return detonations;

    }

    public static int maxDetonation(Map<Integer,List<Integer>> adjList) {


        int detonations = 0;

        // O(V) * O(V+E)
        // O(V) -> loop going through every vertex as the initial one
        // O(V+E) -> queue goes into every vertex and checks every neighbor once
        for (int i = 0; i < adjList.size(); i++) {

            int currentDetonationsCount = 0;

            Deque<Integer> queue = new ArrayDeque<>();
            queue.addLast(i);
            Set<Integer> visited = new HashSet<>();
            visited.add(i);

            // BFS
            while (!queue.isEmpty()) {

                currentDetonationsCount++;
                int node = queue.removeFirst();
                List<Integer> neighbors = adjList.getOrDefault(node, new ArrayList<>());
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.addLast(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            detonations = Math.max(currentDetonationsCount, detonations);
        }

        return detonations;
    }

    public static boolean trigger(int[] bombA, int[] bombB) {

        // manhattam: | x1 - x2| + |y1 - y2| <= R

        int x1 = bombA[0];
        int y1 = bombA[1];
        int r1 = bombA[2];

        int x2 = bombB[0];
        int y2 = bombB[1];

        int manhattam = Math.abs(x1 - x2) + Math.abs(y1 - y2);

        return manhattam <= r1;
    }

    public static void main(String[] args) {



        int[][] bombs = new int[][] {
                {0,0,99},
                {1,0,1},
                {2,0,1},
                {3,0,1},
                {4,0,1},
        };

        detonation(bombs);


    }

}
