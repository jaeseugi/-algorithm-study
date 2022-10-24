package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16173_점프왕쪨리 {

    /**
     * '쩰리’가 정사각형 구역의 외부로 나가는 경우엔 바닥으로 떨어져 즉시 게임에서 패배
     * ‘쩰리’의 출발점은 항상 정사각형의 가장 왼쪽, 가장 위의 칸  → (0,0)
     * ‘쩰리’가 이동 가능한 방향은 오른쪽과 아래  → dx,dy
     * ‘쩰리’가 가장 오른쪽, 가장 아래 칸에 도달하는 순간, 그 즉시 ‘쩰리’의 승리  → (n-1,n-1)
     * ‘쩰리’가 한 번에 이동할 수 있는 칸의 수는, 현재 밟고 있는 칸에 쓰여 있는 수  → move
     */

    // 우, 하
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static boolean isArrived = false;
    static String succ = "HaruHaru";
    static String fail = "Hing";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[][] sq = new int[num][num];


        for (int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < num; j++) {
                sq[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(num, sq, sq[0][0], 0, 0);
        String msg = isArrived ? succ : fail;
        System.out.println(msg);

    }

    public static void dfs(int num, int[][] sq, int move, int x, int y) {

        // 이동하지 못하면 그 즉시 종료
        if(move == 0 ) return;

        // (n-1,n-1) 도달시 도착 종료
        if (x == num - 1 && y == num - 1) {
            isArrived = true;
            return;
        }

        // 이방 탐색 시작
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i] * move;
            int ny = y + dy[i] * move;

            // x,y 중 하나라도 이동한 값이 n-1 보다 클 경우 그 즉시 종료
            if (nx >= num || ny >= num ) continue;
            dfs(num, sq, sq[nx][ny], nx, ny);
        }

    }
}
