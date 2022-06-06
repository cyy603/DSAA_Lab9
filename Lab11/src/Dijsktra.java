import java.io.*;
import java.util.*;

public class Dijsktra {
    private static PriorityQueue<Node> heap;
    private static int[] dist;
    private static Set<Integer> Spt;
    private static int vertex;
    private static ArrayList<ArrayList<Node>> adj;
    static int INF = Integer.MAX_VALUE;

    public Dijsktra(int vertex){
        this.vertex = vertex;
        dist = new int[vertex];
        Spt = new HashSet<Integer>();
        heap = new PriorityQueue<Node>(vertex, new Node());
    }

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        vertex = input.nextInt();
        int edge = input.nextInt();

       adj = new ArrayList<>();
        for (int i = 0; i < edge; i++) {
            ArrayList<Node> temp = new ArrayList<>();
            adj.add(temp);
        }

        for (int i = 0; i < edge; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int weight = input.nextInt();
            Node temp = new Node(y, weight);
            adj.get(x).add(temp);
        }

        Dijsktra dji = new Dijsktra(vertex);

        dji.SPT(1);

        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != INF) {
                output.print(dist[i] + " ");
            }
            else{
                output.print(-1 + " ");
            }
        }
        output.close();
    }

    public void SPT(int start){

        for (int i = 0; i < vertex; i++) {
            dist[i] = INF;
        }



        heap.add(new Node(start, 0));
        dist[start - 1] = 0;

        while (Spt.size() != vertex){
            if (heap.isEmpty()){
                return;
            }
            int u = heap.remove().ele;

            if (Spt.contains(u)){
                continue;
            }

            Spt.add(u);
            findAdj(u);
        }
    }

    public void findAdj(int a){

        int edgeDistance = 0;
        int newDistance = 0;

        for (int i = 0; i < adj.get(a).size(); i++) {
            Node temp = adj.get(a).get(i);

            if (!Spt.contains(temp.ele)){
                edgeDistance = temp.weight;
                newDistance = dist[a - 1] + edgeDistance;

                if (newDistance < dist[temp.ele - 1]){
                    dist[temp.ele - 1] = newDistance;
                }

                heap.add(new Node(temp.ele, dist[temp.ele - 1]));
            }
        }
    }
}

class Node implements Comparator<Node>{
    int ele;
    int weight;

    public Node(int ele, int weight){
        this.ele = ele;
        this.weight = weight;
    }

    public Node(){}

    @Override public int compare(Node node1, Node node2){
        if (node1.weight > node2.weight){
            return 1;
        }
        if (node1.weight < node2.weight){
            return -1;
        }
        return 0;
    }
}

class QReader{
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

class QWriter implements Closeable {
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


