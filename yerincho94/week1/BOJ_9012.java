package week1;

import java.util.*;
import java.io.*;

public class BOJ_9012 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int input = Integer.parseInt(br.readLine()); // 입력받은 값

        for(int i = 0; i < input; i++) {
            sb.append(solve(br.readLine())).append("\n");
        }
        // StringBuilder에 모아둔 모든 결과를 한 번에 출력 (성능 최적화)
        // BufferedWriter가 더 좋지만, StringBuilder도 충분히 빠름
        System.out.print(sb);
    }

    public static String solve(String str) {
        Stack<Character>  stack = new Stack<>(); // push(), pop(), isEmpty()
        // 순회시작
        for(char ch : str.toCharArray()) {
            if(ch == '(') {
                stack.push(ch);
            } else { // ')' 이지만 스택이 비어있을때 주의
                if(stack.isEmpty()) { // 비어있다는건 짝이 없다는 것.
                    return "NO";
                }
                stack.pop();
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }
}


