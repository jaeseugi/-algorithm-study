package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2747_피보나치수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] fibo = new int[n+1];
        fibo[0] = 0;
        if(n >= 1) fibo[1] = 1;
        if(n >= 2) fibo[2] = 1;
        System.out.println(dfs(n, fibo));

//        int n = Integer.parseInt(br.readLine()) - 1;
//        if (n == -1) {
//            System.out.println("0");
//            return;
//        }
//        if( n < 2) {
//            System.out.println("1");
//            return;
//        };
//        int arr[] = new int[n];
//        arr[0] = 1;
//        arr[1] = 2;
//        for (int i = 2; i < n; i++) {
//            arr[i] = arr[i - 1] + arr[i-2];
//        }
//        System.out.println( arr[n-1]);

    }

    private static int dfs(int n , int[] fibo){
        if(  n < 3  || fibo[n] != 0 ) return fibo[n];
        fibo[n] = dfs(n -2 , fibo) + dfs(n -1 , fibo);
        return fibo[n];
    }

}
