package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_1743_음식물피하기 {
    // 상 하 좌 우
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로
        int n = Integer.parseInt(st.nextToken());
        // 가로
        int m = Integer.parseInt(st.nextToken());
        // 음식물 쓰레기 개수
        int k = Integer.parseInt(st.nextToken());
        // 통로
        int[][] path = new int[n][m];
        // 방문 여부
        boolean[][] visit = new boolean[n][m];
        // 가장 큰 쓰레기
        Integer size = Integer.MIN_VALUE;
        // 임시 비교값
        int cnt = 0;

        for (int T = 0; T < k; T++) {
            st = new StringTokenizer(br.readLine());
            path[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (path[i][j] == 1 && !visit[i][j]) {
                    Queue<int[]> queue = new LinkedList<>();
                    visit[i][j] = true;
                    queue.offer(new int[]{i, j});
                    cnt = bfs(n, m, queue, path, visit, cnt);
                    int compare = Integer.compare(size, cnt);
                    size = (compare > 0) ? size : cnt;
                    cnt = 0;
                }
            }
        }

        System.out.println(size);
    }

    private static int bfs(int n, int m, Queue<int[]> queue, int[][] path, boolean[][] visit, int cnt) {
        cnt++;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;
                visit[ni][nj] = true;
                if (path[ni][nj] == 1) {
                    cnt++;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
        return cnt;
    }
}
