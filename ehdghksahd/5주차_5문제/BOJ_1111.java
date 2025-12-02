import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1111

// 앞수 * a + b
// x2 = x1 * a + b
// x3 = x2 * a + b
// x3 - x2 = a (x2 - x1)
// a = (x3 - x2) / (x2 - x1)
public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            list.add(num);
        }

        if(N == 1) { // 수열 수가 1개 일 때
            System.out.println("A");
            return;
        }

        int x1 = list.get(0);
        int x2 = list.get(1);

        if(N == 2) { // 2개 일 때
            if(x1 == x2) System.out.println(x2); // 두 수가 같을때
            else System.out.println("A"); // 아닐 때
            return;
        }

        int x3 = list.get(2);
        int a = 0;
        int b = 0;

        if(x1 == x2) { // 분모 0 체크
            if(x2 != x3) {
                System.out.println("B");
                return;
            }

            a = 1;
            b = 0;

        } else {
            if((x3 - x2) % (x2 - x1) != 0) { // 자연수 체크
                System.out.println("B");
                return;
            }
            a = (x3 - x2) / (x2 - x1);
            b= x2 - (x1 * a);
        }
        for(int i = 1; i < N; i++) { // a, b 값으로 제대로 된 수열인지 체크
            int prev = list.get(i - 1);
            int cur = list.get(i);

            if(cur != (prev * a + b)) {
                System.out.println("B");
                return;
            }
        }
        System.out.println(list.get(N - 1) * a + b); // 모든 조건 만족 후 값 출력
    }
}
