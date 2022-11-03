package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_1245_농장관리 {

    // 첫째 줄에 정수 N(1 < N ≤ 100), M(1 < M ≤ 70)
    // 격자의 높이는 500보다 작거나 같은 음이 아닌 정수

    // 시계 방향
    public static int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static AtomicInteger mt = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        boolean[][] visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int peakCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j] && bfs(n, m, i, j, map, visit)) {
                    peakCnt++;
                }
            }
        }

        System.out.println(peakCnt);
    }

    static private boolean bfs(int n, int m, int i, int j, int[][] map, boolean[][] visit) {
        boolean isPeak = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j, map[i][j]});
        visit[i][j] = true;
        int curVal = map[i][j];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 8; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;
                // 현재 좌표보다 작으면 봉우리가 아님
                if (curVal < map[ni][nj]) {
                    isPeak = false;
                // 같은 경우 BFS
                }else if (curVal == map[ni][nj]) {
                    visit[ni][nj] = true;
                    queue.offer(new int[]{ni, nj, map[ni][nj]});
                // 작은 경우는 다시 방문 하지 못하도록 처리
                }else if (curVal > map[ni][nj]) {
                    notPeak(n, m, ni, nj, map, visit);
                }
            }
        }
        return isPeak;
    }

    static private void notPeak(int n, int m, int i, int j, int[][] map, boolean[][] visit) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j, map[i][j]});
        visit[i][j] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 8; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                int curVal = cur[2];
                if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;
                if (curVal >= map[ni][nj]) {
                    visit[ni][nj] = true;
                    queue.offer(new int[]{ni, nj, map[ni][nj]});
                }
            }
        }
    }
}