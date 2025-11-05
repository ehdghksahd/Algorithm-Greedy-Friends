package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 도키도키 간식드리미
public class BOJ_12789 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new LinkedList<>(); // 학생들이 섞여 있는 큐
        Stack<Integer> stack = new Stack<>(); // 한 명씩만 설 수 있는 공간

        int N = Integer.parseInt(br.readLine()); // 입력값
        String[] input = br.readLine().split(" "); // 입력값 배열에 넣을 용도
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(input[i])); // 입력값을 큐로 옮기기
        }

        int ticket = 1; // 대기표
        while (!queue.isEmpty()) {
            if(queue.peek() == ticket) { // 첫번째 데이터 비교
                queue.poll(); // 큐의 맨 앞 데이터 제거하면서 return
                ticket++; // 순서 1증가
            } else if(!stack.isEmpty() && stack.peek() == ticket) { // 스택도 큐와 같이 처리
                stack.pop(); // 스택 첫 값과 대기표와 같으면 제거하면서 return
                ticket++;
            } else {
                // 둘 다 아니라면 큐를 스택에 쌓아
                stack.push(queue.poll());
            }
        }
        // 이미 쌓은순서가 순서대로 저장이 되었으므로, 남은 학생 처리
        while (!stack.isEmpty() && stack.peek() == ticket) { // 그래도 확인할건 확인해야지
            stack.pop();
            ticket++;
        }

        if(queue.isEmpty() && stack.isEmpty()) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
