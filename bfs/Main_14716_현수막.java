package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    bfs(n, m, board, visit, queue);
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static void bfs(int n, int m, int[][] board, boolean[][] visit, Queue<int[]> queue) {

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int d = 0; d < 8; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;
                visit[ni][nj] = true;
                if (board[ni][nj] == 1) {
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
    }
}
