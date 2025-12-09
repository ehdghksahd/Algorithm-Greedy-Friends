import java.util.*;
import java.io.*;

// 큰 삼각형에서 작은 삼각형....
// Bottom-Up 계산이 더 코드가 간결하다

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> triangle = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            List<Integer> tmp = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }
            triangle.add(tmp);
        }

        for(int i = N - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                int cur = triangle.get(i).get(j);
                int max = Math.max(
                        triangle.get(i + 1).get(j),
                        triangle.get(i + 1).get(j + 1)
                );
                triangle.get(i).set(j, cur + max);
            }
        }
        System.out.println(triangle.get(0).get(0));

    }
}
