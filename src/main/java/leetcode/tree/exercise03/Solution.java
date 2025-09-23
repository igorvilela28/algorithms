package leetcode.tree.exercise03;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Node {
    int key;
    Node left;
    Node right;
    int index;
    int depth;
}

class SearchTree {

    void
    insert(int key) {



    }

    void print() {


    }
}

class Result {

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        // Implementação real omitida

        SearchTree tree = new SearchTree();
        tree.insert(1);

        for (int i = 0; i < indexes.size(); i++) {

            List<Integer> nodes = indexes.get(i);

            Integer a = nodes.get(0);
            Integer b = nodes.get(1);
            if (a != null && a != -1 ) {
                tree.insert(a);
            }
            if (b != null && b != -1 ) {
                tree.insert(b);
            }

        }

        return Collections.emptyList();
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {

        // Caso nenhum argumento seja passado, usa System.in como antes
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader("src/main/java/leetcode/tree/exercise03/input.txt"));

        // Saída em arquivo (poderia ser outro argumento, mas mantive fixo)
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();
        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().trim().split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> queries = IntStream.range(0, queriesCount)
                .mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().trim();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        for (List<Integer> r : result) {
            bufferedWriter.write(
                    r.stream().map(Object::toString).collect(joining(" ")) + "\n"
            );
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
