package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206_벽부수고이동하기 {

    // 0은 이동 가능
    // 1은 벽이라 이동 불가능
    // 1, 1 -> N, M 까지의 최단거리
    //  이때 시작하는 칸과 끝나는 칸도 포함
    // 벽 한개 까지는 부스기 가능
    // 최단거리 출력, 불가능하면 -1 출력
    //  (1, 1)과 (N, M)은 항상 0

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

        // 전체 맵
        int[][] map = new int[n][m];
        boolean[][] visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.substring(j, j + 1));
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1, 0});
        visit[0][0] = true;
        int distance = bfs(n, m, map, queue, visit);
        System.out.println(distance);

    }

    static private int bfs(int n, int m, int[][] map, Queue<int[]> queue, boolean[][] visit) {

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            System.out.println("("+cur[0] + "," + cur[1]+")");
            for (int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                int cnt = cur[2] + 1;
                if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 || (visit[ni][nj]&&cur[3] != 0)) continue;
                if (ni == n - 1 && nj == m - 1) {
                    return cnt;
                }
                if (map[ni][nj] == 1 && cur[3] == 0) {
                    visit[ni][nj] = true;
                    queue.offer(new int[]{ni, nj, cnt, 1});
                }else if(map[ni][nj] == 0) {
                    visit[ni][nj] = true;
                    queue.offer(new int[]{ni, nj, cnt , cur[3]});
                }
            }
        }
        return -1;
    }
}
