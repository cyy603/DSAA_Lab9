import java.io.*;
import java.util.*;

public class Transaction {
    static class adjNode{
        int ele;
        int weight;

        public adjNode(int ele, int weight)
        {
            this.ele = ele;
            this.weight = weight;
        }
    }

    static class Graph{
        int vertex;

        // Pointer to an array containing adjacency lists
        ArrayList<ArrayList<adjNode>> adj;

        public Graph(int vertex){
            this.vertex = vertex;
            adj = new ArrayList<ArrayList<adjNode>>(vertex);

            for (int i = 0; i < vertex; i++) {
                adj.add(new ArrayList<adjNode>());
            }
        }

        public void addEdge(int x, int y, int weight){
            adjNode node = new adjNode(y, weight);
            adj.get(x).add(node);

        }

        public void topSort(int vertex, boolean[] visited, Stack<Integer> stack){
            visited[vertex] = true;

            for (int i = 0; i < adj.get(vertex).size(); i++) {
                adjNode node = adj.get(vertex).get(i);
                if (!visited[node.ele]){
                    topSort(node.ele, visited, stack);
                }
                stack.push(vertex);
            }
        }

        public int longestPath(int start){
            Stack<Integer> stack = new Stack<>();
            int[] dis = new int[vertex];
            boolean[] visited = new boolean[vertex];

            for (int i = 0; i < vertex; i++) {
                if (!visited[i]){
                    topSort(i, visited, stack);
                }
            }

            for (int i = 0; i < vertex; i++) {
                dis[i] = Integer.MIN_VALUE;
            }
            dis[start] = 0;

            while (!stack.isEmpty()){
                int a = stack.peek();
                stack.pop();
                if (dis[a] != Integer.MIN_VALUE){
                    for (int i = 0; i < adj.get(a).size(); i++) {
                        adjNode node = adj.get(a).get(i);
                        if (dis[node.ele] < dis[a] + node.weight){
                            dis[node.ele] = dis[a] + node.weight;
                        }
                    }
                }
            }
            return dis[dis.length - 1];
        }
    }

    public static void main(String[] args) {
        QReader3 input = new QReader3();
        QWriter3 output = new QWriter3();
        int vertex = input.nextInt();
        Graph graph = new Graph(vertex + 1);
        int start = 0;
        int a;
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        HashMap<Integer, Integer> weight = new HashMap<>();

        for (int i = 0; i < vertex; i++) {
            arr.add(new ArrayList<>());
            int cur = input.nextInt();
            int w = input.nextInt();
            weight.put(cur, w);
            arr.get(i).add(cur);
            arr.get(i).add(w);
            a = 1;
            while (a != 0){
                a = input.nextInt();
                arr.get(i).add(a);
            }
            if (arr.get(i).size() == 3){
                start = i + 1;
            }
        }
        int cur = 0;
        a = 0;
        for (int i = 0; i < arr.size(); i++) {
            cur = arr.get(i).get(0);
            for (int j = 2; j < arr.get(i).size() - 1; j++) {
                a = arr.get(i).get(j);
                graph.addEdge(a, cur, weight.get(a));
            }
        }
        int ans = graph.longestPath(start) + arr.get(arr.size() - 1).get(1);
        output.println(ans);
        output.close();
    }

}


class QReader3{
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

class QWriter3 implements Closeable {
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
