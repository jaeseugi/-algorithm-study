package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        // 임시 비교값 - 재귀함수에서 하나의 객체에 대해 동기적으로 증감 처리를 위해  AtomicInteger 사용
        AtomicInteger cnt = new AtomicInteger(0);

        for (int T = 0; T < k; T++) {
            st = new StringTokenizer(br.readLine());
            path[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (path[i][j] == 1 && !visit[i][j]) {
                    visit[i][j] = true;
                    cnt.incrementAndGet();
                    dfs(n, m, i, j, path, visit, cnt);
                    int compare = Integer.compare(size, cnt.get());
                    size = (compare > 0) ? size : cnt.get();
                    cnt.set(0);
                }
            }
        }

        System.out.println(size);
    }

    private static void dfs(int n, int m, int i, int j, int[][] path, boolean[][] visit, AtomicInteger cnt) {
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || visit[ni][nj]) continue;
            visit[ni][nj] = true;
            if (path[ni][nj] == 1) {
                cnt.incrementAndGet();
                dfs(n, m, ni, nj, path, visit, cnt);
            }
        }
    }

}
