package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
    // 위 왼 오른쪽 아래
    private static int[] di = {1, 0, 0, -1};
    private static int[] dj = {0, -1, 1, 0};

    private static int answer = 0;

    // 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.

    // 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만,

    // 그 물고기가 있는 칸은 지나갈 수 있다.

    // 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
    // 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
    // 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
    // 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값

    // 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기

    // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.

    // 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3
    // 0: 빈 칸
    // 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
    // 9: 아기 상어의 위치

    static class Shark {
        int i;
        int j;
        int size;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N x N
        int N = Integer.parseInt(br.readLine());

        int[][] room = new int[N][N];
        boolean[][] visits = new boolean[N][N];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == 9) {
                    visits[i][j] = true;
                    queue.offer(new int[]{i, j, 2, 0});
                }
            }
        }

        bfs(N, room, visits, queue);

    }

    private static void bfs(int N, int[][] room, boolean[][] visits, Queue<int[]> queue) {

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                int size = cur[2];
                int level = cur[3] + 1;

                // 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기
                if (ni < 0 || nj < 0 || ni > N - 1 || nj > N - 1 || room[ni][nj] > size || visits[ni][nj]) continue;
                visits[ni][nj] = true;
                if (room[ni][nj] != 0 && room[ni][nj] <= level) {
                    queue.offer(new int[]{ni, nj, size + 1, level});
                }

            }
        }


    }
}
