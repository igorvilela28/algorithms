package leetcode.bfs_dfs.course_schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1 && prerequisites.length == 0) {
            int[] test = new int[numCourses];
            test[0] = 0;
            return test;
        }

        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        // inDegree = quantidade de arestas chegando no vertice
        int[] inDegrees = new int[numCourses];

        // O(M) -> Number of edges
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            // b -> a
            ArrayList<Integer> neighbors = adjList.getOrDefault(b, new ArrayList<>());
            neighbors.add(a);
            adjList.put(b, neighbors);
            inDegrees[a]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // adicionar todos roots no começo da stack -> O(N)
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int visitedCount = 0;

        // uses Kahn's algorithm to know one topological order
        ArrayList<Integer> topologicalSort = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.remove();
            topologicalSort.add(node);
            visitedCount++;
            ArrayList<Integer> neighbors = adjList.getOrDefault(node, new ArrayList<>());
            for (Integer neighbor : neighbors) {
                inDegrees[neighbor]--;
                if (inDegrees[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // runtime -> O(M) + 2 O(N) => O(M + N)

        //System.out.println(topologicalSort);

        if( visitedCount == numCourses) {
            return topologicalSort
                    .stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
        return new int[]{};
    }
}
