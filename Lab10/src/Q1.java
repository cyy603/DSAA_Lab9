import java.io.*;
import java.util.*;

public class Q1 {

    static class Node{
        int x;
        int y;
        char ele;
        public Node(int x, int y, char ele){
            this.x = x;
            this.y = y;
            this.ele = ele;
        }
    }

    public static boolean bfs(Node[][] graph, Node start, boolean[][] vis){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        vis[start.x][start.y] = true;
        boolean neighbor = false;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            Node[] temp = find_surround(cur, graph);

            for (int i = 0; i < temp.length; i++) {
                Node a = temp[i];
                if (!vis[a.x][a.y]){
                    if (Objects.equals(a.ele, 'W')){
                        queue.offer(a);
                        vis[a.x][a.y] = true;
                        neighbor = true;
                    }
                    else{
                        vis[a.x][a.y] = true;
                    }
                }
            }
        }
        return neighbor;
    }
    public static Node[] find_surround(Node cur, Node[][] graph){
        Node[] temp = new Node[8];

        temp[0] = graph[cur.x - 1][cur.y - 1];
        temp[1] = graph[cur.x - 1][cur. y];
        temp[2] = graph[cur.x - 1][cur.y + 1];
        temp[3] = graph[cur.x][cur.y - 1];
        temp[4] = graph[cur.x][cur.y + 1];
        temp[5] = graph[cur.x + 1][cur.y - 1];
        temp[6] = graph[cur.x + 1][cur.y];
        temp[7] = graph[cur.x + 1][cur.y + 1];

        return temp;
    }

    public static Node check(boolean[][] vis, Node[][] graph){
        for (int i = 0; i < vis.length; i++) {
            for (int j = 0; j < vis[0].length; j++) {
                if (graph[i][j].ele == 'W' && !vis[i][j]){
                    return graph[i][j];
                }
            }
        }
        return new Node(-1,-1,' ');
    }

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int row = input.nextInt();
        int col = input.nextInt();
        boolean[][] vis = new boolean[row + 2][col + 2];

        Node[][] graph = new Node[row + 2][col + 2];
        for (int i = 0; i < row + 2; i++) {
            if (i == 0 || i == row + 1){
                for (int j = 0; j < col + 2; j++) {
                    graph[i][j] = new Node(i, j, ' ');
                    vis[i][j] = true;
                }
                continue;
            }
            String ele = input.next();
            for (int j = 0; j < ele.length() + 2; j++) {
                if (j == 0 || j == ele.length() + 1){
                    graph[i][j] = new Node(i, j, ' ');
                    vis[i][j] = true;
                }
                else{
                    char tmp = ele.charAt(j - 1);
                    graph[i][j] = new Node(i, j, tmp);
                }
            }

        }

        int ans = 0;
        while (!Objects.equals(check(vis, graph).ele, ' ')){
            Node start = check(vis, graph);
            if (bfs(graph, start, vis)){
                ans++;
            }
        }
        output.println(ans);
        output.close();
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
