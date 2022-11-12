package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14716_현수막 {

    public static int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        boolean[][] visit = new boolean[n][m];
        int result = 0;


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && !visit[i][j]) {
                    visit[i][j] = true;
                    dfs(n, m, i, j, board, visit);
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static void dfs(int n, int m, int i, int j, int[][] board, boolean[][] visit) {
        for (int d = 0; d < 8; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;
            visit[ni][nj] = true;
            if (board[ni][nj] == 1) {
                dfs(n, m, ni, nj, board, visit);
            }
        }
    }
}
