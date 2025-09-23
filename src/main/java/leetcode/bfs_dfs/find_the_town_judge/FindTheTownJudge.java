package leetcode.bfs_dfs.find_the_town_judge;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * https://leetcode.com/problems/find-the-town-judge/description/
 */

//The town judge trusts nobody.
//everybody (except for the town judge) trusts the town judge.
//There is exactly one person that satisfies properties 1 and 2.

class FindTheTownJudge {

    public int findJudge2(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) {
            return 1;
        }

        // indegree = contador de quantas arestas entram em um vertice
        // outdegree = contador de quantas arestas saem de um vertice
        // se o vertice tem aresta saindo -> confia em alguem -> outdegree++
        // se o vertice tem aresta entrando -> é confiado por alguém
        // judge -> indegree = n - 1 / outdegree = 0

        int[] indegree = new int[n];
        int[] outdegree = new int[n];


        // Itera sobre todas as arestas -> O(M)
        for (int i = 0; i < trust.length; i++) {

            int a = trust[i][0];
            int b = trust[i][1];
            outdegree[a-1]++;
            indegree[b-1]++;

        }
        int judge = -1;
        // Itera sobre todas os vertices -> O(N)
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                judge = i+1;
                break;
            }
        }

        // tempo -> O(n + m)
        // espaço = O (2 * n) = O(n)

        return judge;

    }

    public int findJudge(int n, int[][] trust) {

        if (n == 1 && trust.length == 0) {
            return 1;
        }

        HashMap<Integer, ArrayList<Integer>> adjMap = new HashMap<>();
        HashSet<Integer> trusted = new HashSet<>();
        HashMap<Integer, Integer> trustCountMap = new HashMap<>();

        for (int i = 0; i < trust.length; i++) {

            int a =  trust[i][0];
            int b =  trust[i][1];
            ArrayList<Integer> list = adjMap.getOrDefault(a, new ArrayList<>());
            list.add(b);
            adjMap.put(a, list);
            trusted.add(b);

            int trustCount = trustCountMap.getOrDefault(b, 0);
            trustCountMap.put(b, trustCount + 1);
        }

        int judge = -1;

        for (int i = 1; i <= n; i++) {

            if (!adjMap.containsKey(i) && trusted.contains(i)) {
                judge = i;
                break;
            }
        }

        if (judge != -1 && trustCountMap.get(judge) == n -1) {
            return judge;
        }

        return -1;
    }

    public static void main(String[] args) {

        // 1 -> 2

        // [[1,3],[2,3]]

        //

        int[][] trust = {
                {1, 3},
                {2, 3},
        };

        FindTheTownJudge solution = new FindTheTownJudge();
        int output1 = solution.findJudge2(3, trust);
        System.out.println(output1);
    }
}

