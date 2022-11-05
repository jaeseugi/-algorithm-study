package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2606_바이러스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computers = Integer.parseInt(br.readLine());
        int connections = Integer.parseInt(br.readLine());

        boolean[][] notVisit = new boolean[computers][computers];

        for (int i = 0; i < connections; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            notVisit[k][v] = true;
        }

        System.out.println(bfs(computers, notVisit));
    }

    private static int bfs(int computers, boolean[][] notVisit) {
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        for (int T = 0; T < computers; T++) {
            if (isNotVisited(notVisit, 0, T)) {
                visited(0, T, notVisit, set);
                queue.offer(new int[]{0, T});
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            for (int T = 0; T < computers; T++) {
                if (isNotVisited(notVisit, i, T)) {
                    visited(i, T, notVisit, set);
                    queue.offer(new int[]{i, T});
                }
                if (isNotVisited(notVisit, j, T)) {
                    visited(j, T, notVisit, set);
                    queue.offer(new int[]{j, T});
                }
            }
        }
        set.remove(0);
        return set.size();
    }

    private static boolean isNotVisited(boolean[][] notVisit, int i, int j) {
        return notVisit[i][j] || notVisit[j][i];
    }

    private static void visited(int i, int j, boolean[][] notVisit, Set<Integer> set) {
        notVisit[i][j] = false;
        notVisit[j][i] = false;
        set.add(i);
        set.add(j);
    }
}