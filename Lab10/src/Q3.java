import java.io.*;
import java.util.*;

public class Q3 {
    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] enumerate(int num, int node){
        int[][] pairs = new int[2][num];
        int a = 0;
        int index = 0;
        for (int i = 0; i < num; i++) {
            a++;
            for (int j = i; j < node - 1; j++) {
               pairs[0][index] = a;
               pairs[1][index] = j + 2;
               index++;
            }
        }
        return pairs;
    }

    public static void sort(ArrayList<Integer> arr){
        for (int i = 0; i < arr.size() - 1; i++) {
           if (arr.get(i) > arr.get(i + 1)){
               int temp = arr.get(i);
               arr.set(i, arr.get(i + 1));
               arr.set(i + 1, temp);
           }
        }
    }

    public static void Default(Map<Integer, ArrayList<Integer>> arr){
        int index = 0;
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            graph.put(i + 1, new ArrayList<>());
        }
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
            }
        }
    }

    public static void main(String[] args) {
        QReader2 input = new QReader2();
        QWriter2 output = new QWriter2();

        int node = input.nextInt();
        int[][] graph = new int[node][2];
        Node[] arr = new Node[node];
        Map<Integer, ArrayList<Integer>> coor = new HashMap<>();
        for (int i = 0; i < node; i++) {
            coor.put(i, new ArrayList<>());
        }

        for (int i = 0; i < node; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            if (i == 0)
                coor.get(i).add(y);
        }

        for (ArrayList<Integer> temp : coor.values()) {
            sort(temp);
        }


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

