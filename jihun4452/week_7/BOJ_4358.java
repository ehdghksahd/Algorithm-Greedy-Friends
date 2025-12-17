import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map = new TreeMap<>();
        String line;
        int total = 0;

        while ((line = br.readLine()) != null) {
            if (line.length() == 0) continue;
            map.put(line, map.getOrDefault(line, 0) + 1);
            total++;
        }

        StringBuilder sb = new StringBuilder();

        for (String key : map.keySet()) {
            double percent = (map.get(key) * 100.0) / total;
            sb.append(key)
              .append(" ")
              .append(String.format("%.4f", percent))
              .append("\n");
        }

        System.out.print(sb.toString());
    }
}
