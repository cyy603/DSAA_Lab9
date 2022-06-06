import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Kosaraju {
    static int vertex;
    static ArrayList<Integer>[] adj;

    public static void DFS(boolean[] visited, int start, ArrayList<Integer>[] adj){
        visited[start] = true;
        int ind = 0;

        Iterator<Integer> iter = adj[start].iterator();
        while (iter.hasNext()){
            ind = iter.next();
            if (!visited[ind]){
                DFS(visited, ind, adj);
            }
        }
    }

    public static ArrayList<Integer>[] reverse(ArrayList<Integer>[] adj){
        ArrayList<Integer>[] new_adj = new ArrayList[vertex];
        for (int i = 0; i < vertex; i++) {
            new_adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                new_adj[adj[i].get(j)].add(i);
            }
        }
        return new_adj;
    }

    public static boolean isSOC(int start){
        boolean[] visited = new boolean[vertex];
        DFS(visited, start, adj);

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i])
                return false;
        }
        ArrayList<Integer>[] re_adj= reverse(adj);

        boolean[] re_visited = new boolean[vertex];
        DFS(re_visited, start, re_adj);

        for (int i = 0; i < re_visited.length; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        QReader2 input = new QReader2();
        QWriter2 output = new QWriter2();

        vertex = input.nextInt();
        int edges = input.nextInt();

        adj = new ArrayList[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new ArrayList<>();
        }

        int start = 0;
        for (int i = 0; i < edges; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            adj[x - 1].add(y - 1);

            if (i == 0)
                start = x;
        }

        if (isSOC(start)){
            output.println("Yes");
        }
        else{
            output.println("No");
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



