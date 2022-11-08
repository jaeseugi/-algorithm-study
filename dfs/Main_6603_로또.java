package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6603_로또 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        int[] lotto = new int[6];

        StringBuffer sb = new StringBuffer();

        while (true) {
            str = br.readLine();
            if (str.equals(0)) break;

            StringTokenizer st = new StringTokenizer(str);
            int size = Integer.parseInt(st.nextToken());
            int[] number = new int[size];
            boolean[] visit = new boolean[size];
            for (int i = 0; i < size; i++) {
                number[i] = Integer.parseInt(st.nextToken());
            }

            dfs(size, 0, 0, visit, number, lotto, sb);
            System.out.println();
        }
    }

    private static void dfs(int size, int cur, int p, boolean[] visit, int[] number, int[] lotto, StringBuffer sb) {
        if (p == 6) {
            for (int i : lotto) {
                sb.append(i).append(" ");
            }
            sb.setLength(sb.length() - 1);
            System.out.println(sb.toString());
            sb.setLength(0);
            return;
        }
        for (int i = cur; i < size; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            lotto[p] = number[i];
            dfs(size, i, p + 1, visit, number, lotto, sb);
            visit[i] = false;
        }
    }
}
