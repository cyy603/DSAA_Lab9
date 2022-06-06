import java.io.*;
import java.util.*;

public class Tree {
    static boolean[] vis;

    public static boolean valid(int[][] graph, int vertex, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        vis = new boolean[vertex];

        while (!queue.isEmpty()){
            int cur = queue.poll();
            vis[cur] = true;
            //gain adjacent point
            for (int i = 0; i < vertex; i++) {
                if (graph[cur][i] == 1){
                    if (vis[i]){
                        return false;
                    }
                    vis[i] = true;
                    graph[cur][i] = 0;
                    graph[i][cur] = 0;
                    queue.add(i);
                }
            }
        }

        for (int i = 0; i < vertex; i++) {
            if (!vis[i]){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        QReader1 input = new QReader1();
        QWriter1 output = new QWriter1();

        int vertex = input.nextInt();
        int edges = input.nextInt();
        int start = 0;

        int[][] graph = new int[vertex][vertex];

        for (int i = 0; i < edges; i++) {
            int row = input.nextInt();
            int col = input.nextInt();

            graph[row - 1][col - 1] = 1;
            graph[col - 1][row - 1] = 1;

            if (i == 0){
                start = row - 1;
            }
        }

        if (valid(graph, vertex, start)){
            output.println("tree");
        }
        else{
            output.println("graph");
        }

        output.close();
    }
}

class QReader1{
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

class QWriter1 implements Closeable {
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
