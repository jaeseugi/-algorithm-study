package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1012_유기농배추 {
    //  상 하 좌 우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스
        int t = Integer.parseInt(br.readLine());

        for (int T = 0; T < t; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 가로 길이
            int m = Integer.parseInt(st.nextToken());
            // 세로 길이
            int n = Integer.parseInt(st.nextToken());
            // 배추 갯수
            int c = Integer.parseInt(st.nextToken());
            //  밭
            int[][] board = new int[n][m];
            //  방문한 밭
            boolean[][] visit = new boolean[n][m];
            // 지렁이 수
            int cnt = 0;

            for (int j = 0; j < c; j++) {
                StringTokenizer s = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(s.nextToken());
                int y = Integer.parseInt(s.nextToken());
                board[y][x] = 1;
            }

            // 세로
            for (int i = 0; i < n; i++) {
                // 가로
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1 && !visit[i][j]) {
                        visit[i][j] = true;
                        dfs(n, m, board, i, j, visit);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    /**
     *
     * @param n 세로 길이
     * @param m 가로 길이
     * @param board 밭
     * @param i 접근한 세로 좌표
     * @param j 접근한 가로 좌표
     * @param visit 방문한 밭
     */
    static void dfs(int n, int m, int[][] board, int i, int j, boolean[][] visit) {

        for (int d = 0; d < 4; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];

            if (ni > n - 1 || nj > m - 1 || ni < 0 || nj < 0 || visit[ni][nj]) continue;

            visit[ni][nj] = true;

            if (board[ni][nj] == 1) dfs(n, m, board, ni, nj, visit);
        }
    }
}
