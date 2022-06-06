import java.io.*;
import java.util.StringTokenizer;

public class Matrix {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int node = input.nextInt();
        long edge = input.nextLong();

        int[][] matrix = new int[node][node];

        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                matrix[i][j] = -1;
            }
        }

        for (int i = 0; i < edge; i++) {
            int row = input.nextInt();
            int col = input.nextInt();
            int wei = input.nextInt();

            matrix[row - 1][col - 1] = wei;
        }

        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                if (j == node - 1){
                    output.println(matrix[i][j]);
                }
                else{
                    output.print(matrix[i][j] + " ");
                }
            }
        }
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
