package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1941_소문난칠공주 {

//    이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
//    강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
//    화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
//    그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
//    'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)

    private static int[] di = {-1, 1, 0, 0};
    private static int[] dj = {0, 0, -1, 1};
    private static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 5;
        char[][] students = new char[n][n];

        for (int i = 0; i < n; i++) {
            students[i] = br.readLine().toCharArray();
        }

        boolean[][] reals = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                if (students[i][j] == 'S' && !visits[i][j]) {
                if (students[i][j] == 'S' && !reals[i][j]) {
                    boolean[][] visits = new boolean[n][n];
                    visits[i][j] = true;
                    dfs(n, i, j, 1, 0, students, visits,reals);
                }
            }
        }
        System.out.println(total);
    }

    private static void dfs(int n, int i, int j, int s_cnt, int y_cnt, char[][] students, boolean[][] visits, boolean[][] reals) {


//        if (y_cnt > 3) {
//            visits[i][j] = false;
//            return;
//        }

        if ((s_cnt + y_cnt) == 7 && s_cnt >= 4) {
            System.out.println(String.format("(%S,%S)", i, j));
            for (int ri = 0; ri < n; ri++) {
                for (int rj = 0; rj < n; rj++) {
//                if (students[i][j] == 'S' && !visits[i][j]) {
                    if (visits[ri][rj]) {
                        reals[ri][rj] = true;
                    }
                }
            }
            System.out.println("===============");
            for (boolean[] visit : visits) {
                for (boolean b : visit) {
                    System.out.print(b +  " ");
                }
                System.out.println();
            }
            System.out.println("===============");
            total++;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

//            if (ni < 0 || nj < 0 || ni > n - 1 || nj > n - 1 || visits[ni][nj] || reals[ni][nj]) continue;
            if (ni < 0 || nj < 0 || ni > n - 1 || nj > n - 1 || visits[ni][nj] ) continue;

            int ns_cnt = s_cnt;
            int ny_cnt = y_cnt;

            if (students[ni][nj] == 'S') ns_cnt = ns_cnt +1;
            else ny_cnt = ny_cnt +1;

            visits[ni][nj] = true;
            dfs(n, ni, nj, ns_cnt, ny_cnt, students, visits,reals);
            visits[ni][nj] = false;
        }
    }
}
