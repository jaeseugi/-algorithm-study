package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main_1245_농장관리 {

    // 첫째 줄에 정수 N(1 < N ≤ 100), M(1 < M ≤ 70)
    // 격자의 높이는 500보다 작거나 같은 음이 아닌 정수

    // 시계 방향
    public static int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};

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

        // 봉우리 개수
        int peakCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    AtomicBoolean isPeak = new AtomicBoolean(true);
                    dfs(n, m, i, j, map, visit, isPeak);
                    if (isPeak.get()) {
                        peakCnt++;
                    }
                }
            }
        }
        System.out.println(peakCnt);
    }

    static private void dfs(int n, int m, int i, int j, int[][] map, boolean[][] visit, AtomicBoolean isPeak) {
        visit[i][j] = true;

        for (int d = 0; d < 8; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            int cur = map[i][j];

            if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;

            // 인접한곳에 자신보다 낮으면 두번다시 방문할 필요 없음
            if (cur > map[ni][nj]) {
                // 봉우리가 될 수 없는 곳은 다른 재귀함수를 통해 인접한 곳이 해당 지점과 같거나 낮은 곳은 다시 방문하지 않도록 처리
                notPeak(n, m, ni, nj, map, visit);
            }

            // 더 높은 곳이 존재하면 봉우리가 아니므로 봉우리 여부를 변경
            if (cur < map[ni][nj]) {
                isPeak.set(false);
                continue;
            }

            // 같은 크기가 있으면 거기로 넘어가자
            if (cur == map[ni][nj]) dfs(n, m, ni, nj, map, visit, isPeak);
        }
    }

    /**
     *  봉우리가 될 수 없는 곳은 방문함으로 변경
     */
    static private void notPeak(int n, int m, int i, int j, int[][] map, boolean[][] visit) {
        visit[i][j] = true;
        for (int d = 0; d < 8; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            int cur = map[i][j];

            if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;

            // 봉우리가 아닌 곳과 그 인접한 곳의 높이가 같거나 낮으면 마찬가치로 두번 다시 방문할 필요 없음
            if (cur >= map[ni][nj]) {
                notPeak(n, m, ni, nj, map, visit);
            }
        }
    }
}
