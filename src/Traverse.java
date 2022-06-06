import java.util.*;

public class Traverse {
    public static  Map<Integer, ArrayList<Integer>> initialize(int[][] edges, int vertex){
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();

        for (int i = 0; i < vertex; i++) {
            graph.put(i + 1, new ArrayList<>());
            graph.get(i + 1).add(i + 1);
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            graph.get(x).add(y);
        }
        return graph;
    }

    public static boolean finish(boolean[] list){
        for (boolean vis : list) {
            if (!vis)
                return false;
        }
        return true;
    }

    public static int find(boolean[] list){
        for (int i = 0; i < list.length; i++) {
            if (!list[i]){
                return i;
            }
        }
        return -1;
    }

    public static void traverse(Map<Integer, ArrayList<Integer>> graph, int start, int vertex){
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        queue.offer(start);
        boolean[] vis = new boolean[vertex];
        int[] res = new int[vertex];
        int max = 0;
        int a = 0;
        boolean done = false;

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int vertex = input.nextInt();
        int edge = input.nextInt();
        int max = 0;
        int[][] edges = new int[edge][2];

        for (int i = 0; i < edge; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            edges[i][0] = x;
            edges[i][1] = y;
            if (max < x){
                max = x;
            }
            if (max < y){
                max = y;
            }
        }

        Map<Integer, ArrayList<Integer>> graph = initialize(edges, vertex);
    }
}
