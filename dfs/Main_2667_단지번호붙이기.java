package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_2667_단지번호붙이기 {

    private static int[] di = {-1, 1, 0, 0};
    private static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] maps = new char[n][n];
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (maps[i][j] == '1') {
                    AtomicInteger integer = new AtomicInteger(0);
                    dfs(n, i, j, integer, maps);
                    priorityQueue.add(integer.get());
                }
            }
        }

        System.out.println(priorityQueue.size());
        while (!priorityQueue.isEmpty()) System.out.println(priorityQueue.poll());
    }

    private static void dfs(int n, int curi, int curj, AtomicInteger integer, char[][] maps) {
        integer.incrementAndGet();
        maps[curi][curj] = 0;
        for (int d = 0; d < 4; d++) {
            int ni = curi + di[d];
            int nj = curj + dj[d];
            if (ni < 0 || nj < 0 || ni > n -1 || nj > n-1 || maps[ni][nj] != '1') continue;
            dfs(n, ni, nj, integer, maps);
        }
    }
}
