package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1012_유기농배추 {
    //  상 하 좌 우
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

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
            // 밭
            int[][] board = new int[n][m];
            // 접근한 밭
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
                        Queue<int[]> queue = new LinkedList<>();
                        queue.offer(new int[]{i, j});
                        bfs(n, m, board, visit, queue);
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
     * @param visit 방문한 밭
     * @param queue
     */
    static void bfs(int n, int m, int[][] board, boolean[][] visit, Queue<int[]> queue) {

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int i = cur[0];
            int j = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                // 이중 배열 범위를 벗어나므로 종료
                if (ni > n - 1 || nj > m - 1 || ni < 0 || nj < 0 || visit[ni][nj]) {
                    continue;
                }

                visit[ni][nj] = true;

                if (board[ni][nj] == 1) {
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
    }
}
