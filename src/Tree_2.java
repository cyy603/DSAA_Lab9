import java.io.*;
import java.util.*;

public class Tree_2 {
    public static Map<Integer, Set<Integer>> initialize(int vertex, int[][] edges){
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < vertex; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u - 1).add(v);
            graph.get(v - 1).add(u);
        }

        return graph;
    }

    public static boolean valid(int vertex, Map<Integer, Set<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[vertex];

        queue.offer(start);
        vis[start - 1] = true;

        while (!queue.isEmpty()){
            int cur = queue.poll();
            for (Integer adjacent : graph.get(cur - 1)) {
                if (vis[adjacent - 1]){
                    continue;
                }
                vis[adjacent - 1] = true;
                queue.offer(adjacent);
            }
        }

        for (boolean vi : vis) {
            if (!vi) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        QReader2 input = new QReader2();
        QWriter2 output = new QWriter2();

        int vertex = input.nextInt();
        int edge = input.nextInt();

        int[][] edges = new int[edge][2];

        for (int i = 0; i < edge; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            edges[i][0] = node1;
            edges[i][1] = node2;
        }
        int start = edges[0][0];
        Map<Integer, Set<Integer>> graph = initialize(vertex, edges);

        if (valid(vertex, graph, start)){
            output.println("tree");
        }
        else{
            output.println("graph");
        }

        output.close();
    }
}


class QReader2{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter2 implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
