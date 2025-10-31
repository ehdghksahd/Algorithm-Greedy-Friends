package week1;

import java.io.*;
import java.util.*;

public class BOJ_10773 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 입력 개수
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) stack.pop(); // 0이면 pop
            else stack.push(num);      // 아니면 push
        }

        int sum = 0;
        for (int x : stack) sum += x;

        System.out.println(sum); // 최종 결과 한 줄 출력
    }
}

