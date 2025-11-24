package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// 회전하는 큐
public class BOJ_1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> deque = new LinkedList<>(); // 양방향

        String[] firstLine = br.readLine().split(" "); // 첫 줄 입력값
        int N = Integer.parseInt(firstLine[0]); // deque의 크기
//        int M = Integer.parseInt(firstLine[1]); // 뽑아내야하는 숫자의 갯수
        for(int i = 1; i <= N; i++) {
            deque.offerLast(i); // deque의 크기를 1~N번 순으로 넣는다.
        }

        String[] secondLine = br.readLine().split(" "); // 두번째 줄 입력값
        int count = 0; // 누적용
        for(int i=0; i < secondLine.length; i++){
            int targetNum = Integer.parseInt(secondLine[i]);

            int idx = 0;
            for(int num : deque) { // 현재 deque에서 targetNum의 인덱스 찾기
                if(num == targetNum) break;
                idx++;
            }
            if(idx <= deque.size() / 2) { // deque크기의 가운데로 기준점
                for (int j = 0; j < idx; j++) {
                    // 기준점보다 작다는 뜻 = 왼쪽으로 이동이 더 빠르다는 뜻
                    // 기준점과 동일하면 왼/오 상관없으니 왼쪽으로 통일
                    deque.offerLast(deque.pollFirst()); // 앞쪽 제거 후 뒤에 추가한다.(순서대로 추가한다)
                    count++;
                }
            } else {
                for (int j = 0; j < deque.size() - idx; j++) { // 기준점 오른쪽 개수만큼 반복
                    // 오른쪽으로 이동이 더 빠름.
                    deque.offerFirst(deque.pollLast()); // 뒤쪽 제거 후 앞에 추가한다.
                    count++;
                }
            }
            deque.pollFirst(); // 목표 숫자가 맨 앞에 왔으므로 제거
        }
        System.out.println(count);
    }
}
