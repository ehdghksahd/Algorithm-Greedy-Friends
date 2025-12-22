import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeMap<String, Double> map = new TreeMap<>();

        String name;
        double cnt = 0;
        while((name = br.readLine()) != null) { // 입력 갯수 주어지지 않았을 때,
            map.merge(name, 1.0, Double::sum);
            cnt++;
        }
        for(String key: map.keySet()) {
            double ratio = (map.get(key) / cnt) * 100.0;

            // double answer = Math.round(ratio * 10000) / 10000.0; // 4째자리까지 반올림 // 12여도 12.0000 이렇게 출력 해야됨 ㄷㄷ
            String formattedRatio = String.format("%.4f", ratio);

            System.out.println(key + " " + formattedRatio);
        }

    }
}
