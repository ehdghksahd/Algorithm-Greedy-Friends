// BOJ / 4358 / 생태학 / 실버2
package somyoung.week7;

import java.io.*;
import java.util.*;

public class BOJ_4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, Integer> map = new TreeMap<>(); // 사전순 정렬 필요 -> TreeMap 사용

        while(true){
            String tree = br.readLine();
            if(tree == null) break; // 더 이상 받을 입력이 없으면 break

            map.put(tree, map.getOrDefault(tree, 0) + 1);
        }

        int cnt = 0;

        for(int value : map.values()){
            cnt += value; // 전체 나무 개수 카운팅
        }

        for(String key : map.keySet()){
            double percent = (double) map.get(key) / cnt * 100;
            System.out.printf("%s %.4f\n", key, percent);
        }
    }
}

