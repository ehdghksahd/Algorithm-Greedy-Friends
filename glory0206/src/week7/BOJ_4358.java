package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class BOJ_4358 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name; // 입력하는 나무의 이름
        int total = 0; // 총 그루

        Map<String, Integer> map = new TreeMap<>(); // 영어이름으로 오름차순 정렬을 위해 TreeMap 사용

        while ((name = br.readLine()) != null) {
            if (name.length() == 0){
                continue;
            }
            map.put(name, map.getOrDefault(name, 0) + 1); // name이 없다면 (name, 0)으로 추가, 이미 있다면 있던 값에 1을 추가
            total++;
        }

        StringBuilder sb = new StringBuilder();
        for(String key: map.keySet()){
            double percent = (double) map.get(key) / total * 100;
            sb.append(key);
            sb.append(" ");
            sb.append(String.format("%.4f", percent));
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
