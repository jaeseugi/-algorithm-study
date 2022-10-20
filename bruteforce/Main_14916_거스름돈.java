package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * packageName :  bruteforce
 * fileName : Main_14916_거스름돈
 * author :  wotjr210
 * date : 2022/10/20
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2022/10/20                wotjr210             최초 생성
 */
public class Main_14916_거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int change = Integer.parseInt(reader.readLine());

        int[] arr = {0, 3, 1, 4, 2, 0, 3,1,4,2};
        int flag = 0;
        int rsult = 0;

        if (change < 10) {
            if(change%2==0) rsult = change / 2;
            else if(change == 1 ||change == 3) rsult = -1;
            else if(change == 5) rsult = 1;
            else if(change == 7) rsult = 2;
            else if(change == 8) rsult = 3;
            else if(change == 9) rsult = 3;
        }else{
            flag = change % 10;
            rsult += arr[flag];
            change = change - arr[flag] * 2;
            rsult += (change/5);
        }
        System.out.print(rsult);
    }

}
