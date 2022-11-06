package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2644_촌수계산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int personA = Integer.parseInt(st.nextToken()) -1 ;
        int personB = Integer.parseInt(st.nextToken()) -1;
        int relations = Integer.parseInt(br.readLine());

        // [부모][자식]
        boolean family[][] = new boolean[people][people];
        int[] check = new int[people];

        for (int T = 0; T < relations; T++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            change(family, i, j, true);
        }

        calculate(people, personA, personB, family, check);


    }

    private static void calculate(int people, int personA, int personB, boolean[][] family , int[] check) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(personA);
        int cnt = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            for (int T = 0; T < people; T++) {
                if(family[cur][T] && check[T] == 0){
//                    System.out.println(String.format("%S -> %S", cur, T));
                    check[T] = check[cur] + 1;
                    queue.add(T);
                }
            }
        }
        System.out.println(check[personB] == 0 ? -1 : check[personB]);
    }

    private static void change(boolean[][] family, int personA, int T, boolean x) {
        family[personA][T] = family[T][personA] = x;
    }

}
