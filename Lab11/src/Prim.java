import java.io.*;
import java.util.*;

public class Prim {
    static int vertex;
    static HashMap<Integer, Integer>[] adj;
    static class PQ_Node{
        int index;
        int key;
    }

    static class comparator implements Comparator<PQ_Node>{
        @Override
        public int compare(PQ_Node node0, PQ_Node node1)
        {
            return node0.key - node1.key;
        }
    }

    public static int MST(int vertex, HashMap<Integer, Integer>[] adj){
        int ans = 0;
        boolean[] visited = new boolean[vertex];
        PQ_Node[] pqNodes = new PQ_Node[vertex];

        for (int i = 0; i < vertex; i++) {
            pqNodes[i] = new PQ_Node();
            pqNodes[i].index = i;
            pqNodes[i].key = Integer.MAX_VALUE;
        }

        visited[0] = true;
        pqNodes[0].key = 0;
        TreeSet<PQ_Node> queue = new TreeSet<>(new comparator());

        queue.addAll(Arrays.asList(pqNodes).subList(0, vertex));

        while (!queue.isEmpty()){
            PQ_Node node = queue.pollFirst();
            assert node != null;
            visited[node.index] = true;

            ans += node.key;

            for (Integer key : adj[node.index].keySet()) {
                if (!visited[key]){
                    if (pqNodes[key].key > adj[node.index].get(key)){
                        queue.remove(pqNodes[key]);
                        pqNodes[key].key = adj[node.index].get(key);
                        queue.add(pqNodes[key]);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        QReader1 input = new QReader1();
        QWriter1 output = new QWriter1();

        vertex = input.nextInt();
        int edges = input.nextInt();
        adj = new HashMap[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new HashMap<>();
        }
        for (int i = 0; i < edges; i++) {
            int start = input.nextInt();
            int end = input.nextInt();
            int weight = input.nextInt();
            adj[start - 1].put(end - 1, weight);
            adj[end - 1].put(start - 1, weight);
        }

        int ans = MST(vertex, adj);

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


