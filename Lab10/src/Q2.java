import java.util.*;
import java.io.*;

public class Q2 {
    static int[] visited;

    public static void bfs(Map<Integer, ArrayList<Integer>> graph, int start, int vertex){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[vertex];
        queue.offer(start);

        while (!queue.isEmpty()){
            int cur = queue.poll();

            for (int adj : graph.get(cur)){
                if (!vis[adj - 1]){
                    vis[adj - 1] = true;
                    visited[adj - 1]++;
                    queue.offer(adj);
                }
            }
        }
    }

    public static int check(int custom){
        int ans = 0;
        for (int i = 0; i < visited.length; i++) {
            int temp = visited[i];
            if (temp == custom){
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        QReader1 input = new QReader1();
        QWriter1 output = new QWriter1();
        int cust = input.nextInt();
        int area = input.nextInt();
        int edges = input.nextInt();

        int[] custom = new int[cust];
        visited = new int[area];
        for (int i = 0; i < custom.length; i++) {
            custom[i] = input.nextInt();
        }
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < area; i++) {
            graph.put(i + 1, new ArrayList<>());
            graph.get(i + 1).add(i + 1);
        }

        for (int i = 0; i < edges; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            graph.get(x).add(y);
        }

        for (int i = 0; i < custom.length; i++) {
            bfs(graph, custom[i], area);
        }
        int ans = check(cust);
        output.println(ans);
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
