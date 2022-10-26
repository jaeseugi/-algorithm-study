package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4963_섬의개수 {
    // 시계방향으로 팔방 탐색
    public static int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);
            // 가로
            int w = Integer.parseInt(st.nextToken());
            // 세로
            int h = Integer.parseInt(st.nextToken());
            //  입력 마지막 (0,0) 프로그램 종료
            if (w == 0 && h == 0) return;
            // 섬과 바다
            int[][] arr = new int[h][w];
            // 방문한 섬과 바다
            boolean[][] visit = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (arr[i][j] == 1 && !visit[i][j]) {
                        Queue<int[]> queue = new LinkedList<>();
                        visit[i][j] = true;
                        queue.offer(new int[]{i, j});
                        bfs(h, w, arr, visit, queue);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);

        }
    }

    private static void bfs(int h, int w, int[][] arr, boolean[][] visit, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 8; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];

                if (ni < 0 || nj < 0 || ni > h - 1 || nj > w - 1 || visit[ni][nj]) continue;

                visit[ni][nj] = true;

                if (arr[ni][nj] == 1) queue.offer(new int[]{ni, nj});
            }
        }
    }
}
