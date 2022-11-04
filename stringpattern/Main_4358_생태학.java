package stringpattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_4358_생태학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map = new HashMap<>();
        String str;
        int cnt = 0;
        while (( str = br.readLine()) != null && !str.isEmpty()) {
            cnt++;
            if(map.containsKey(str)){
                map.put(str, map.get(str) + 1);
                continue;
            }
            map.put(str, 1);
        }

        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.sort((s1, s2) -> s1.compareTo(s2));
        for (String key : keyList) {
            double dVal =  ((double) map.get(key)) /((double)cnt) * 100;
            System.out.println(key + " " + String.format("%.4f", dVal));
        }

    }
}
