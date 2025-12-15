package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 미국 전역의 나무들이 주어졌을 때, 각 종이 전체에서 몇 %를 차지하는지 구하라.
 * 각 종이 전체에서 차지하는 비율(%)을 사전순으로 출력하기 (TreeMap활용)
 * 소수점 4자리까지!
 */
// 생태학(실버2) - HashMap/문자열
public class BOJ_4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // TreeMap: 자동 사전순 정렬
        Map<String, Integer> map = new TreeMap<>();

        int total = 0;  // 전체 나무 개수

        String tree;
        while ((tree = br.readLine()) != null && !tree.isEmpty()) {
            // 빈도 카운트
            if (map.containsKey(tree)) {
                // 이미 있으면 +1
                map.put(tree, map.get(tree) + 1);
            } else {
                // 없으면 1로 시작
                map.put(tree, 1);
            }
            total++;
        }

        // 비율 계산 + 출력
        for (String name : map.keySet()) {
            int count = map.get(name);
            double percentage = count * 100.0 / total;

            System.out.printf("%s %.4f\n", name, percentage);
        }

    }
}
