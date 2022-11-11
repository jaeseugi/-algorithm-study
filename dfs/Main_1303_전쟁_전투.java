package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1303_전쟁_전투 {

    private static int[] di = {-1, 1, 0, 0};
    private static int[] dj = {0, 0, -1, 1};

    private static int W_SUM = 0;
    private static int B_SUM = 0;

    private static int W_CNT = 0;
    private static int B_CNT = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로
        int n = Integer.parseInt(st.nextToken());
        // 세로
        int m = Integer.parseInt(st.nextToken());

        char[][] wars = new char[m][n];
        boolean[][] visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            wars[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (wars[i][j] == 'W' && !visit[i][j]) {
                    W_CNT = 0;
                    dfs('W',m, n, i, j, wars, visit);
                    W_SUM += Math.pow(W_CNT, 2);
                } else if (wars[i][j] == 'B' && !visit[i][j]) {
                    B_CNT = 0;
                    dfs('B',m, n, i, j, wars, visit);
                    B_SUM += Math.pow(B_CNT, 2);
                }
            }
        }

        System.out.println(W_SUM);
        System.out.println(B_SUM);

    }

    private static void dfs(char key, int m, int n, int i, int j, char[][] wars, boolean[][] visit) {
        visit[i][j] = true;
//        System.out.println(String.format("%S (%S,%S)", key, i, j));
        if('W' == key) W_CNT++;
        else  B_CNT++;

        for(int d = 0 ; d < 4 ;d ++){
            int ni = i + di[d];
            int nj = j + dj[d];

            if( ni < 0 || nj < 0 || ni > m -1 || nj > n-1 || visit[ni][nj] || wars[ni][nj] != key ) continue;

            visit[ni][nj] = true;
            dfs(key, m, n, ni, nj, wars, visit);
        }
    }
}
