package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 벽을 깬 사람은 벽을 깬 맵을 기준으로 방문
 * 벽을 부수지 않은 사람은 벽을 깨지않은 맵을 기준으로 방문
 * 위와 같은 로직이 가능한 이유는 BFS에서는 자식 노드를 기준으로 다음 방향으로 가는 방향이 동일 합니다.
 * 그래서 자식 노드들 중 길을 찾아갈때 자식의 자식의 노드들 중 같은 자식의 자식의 노드가 있다면 두번 돌 필요가 없음을 의미합니다.
 * 한번이로 깬사람은 벽을 깬 좌표를 기준으로 같은 길을 타게 되므로 큐를 생성해서 길을 찾을 필요가 없다.
 */
public class Main_2206_벽부수고이동하기 {

    // 0은 길이라 이동 가능
    // 1은 벽이라 이동 불가능
    // 1, 1 -> N, M 까지의 최단거리
    // 이때 시작하는 칸과 끝나는 칸도 포함
    // 벽 한개 까지는 부수기 가능
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

        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        System.out.println(bfs(n, m, map));
    }

    static private int bfs(int n, int m, char[][] map) {
        //  1x1 맵인 경우 1로 종료
        if (n == 1 && m == 1) {
            return 1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1, 0});

        boolean[][] no_broken = new boolean[n][m];
        boolean[][] broken = new boolean[n][m];
        no_broken[0][0] = true;
        broken[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                int cnt = cur[2] + 1;
                boolean destroy = (cur[3] == 1);

                // 마지막에 제일 먼저 도달하면 종료 : BFS에서 제일 처음 도착한 큐가 최단거리
                if (ni == n - 1 && nj == m - 1) {
                    return cnt;
                }

                if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1) continue;

                if (map[ni][nj] == '0') {
                    if (destroy && !broken[ni][nj]) {
                        broken[ni][nj] = true;
                        queue.offer(new int[]{ni, nj, cnt, cur[3]});
                    } else if (!destroy && !no_broken[ni][nj]) {
                        no_broken[ni][nj] = true;
                        queue.offer(new int[]{ni, nj, cnt, cur[3]});
                    }
                } else if(map[ni][nj] == '1' && !broken[ni][nj]){
                    if(destroy) continue;
                    queue.offer(new int[]{ni, nj, cnt, 1});
                }
            }
        }
        return -1;
    }
}
