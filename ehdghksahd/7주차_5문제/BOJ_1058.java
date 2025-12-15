import java.util.*;
import java.io.*;

// A B C
// 2-친구가 되기 위한 조건 -> 서로 친구 되거나 같은 사람을 친구로 가지면 된다?
// A - B 이거나 A - C , B - C 이면 2-친구가 성립?

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<ArrayList<Integer>> tree = new ArrayList<>();

        for(int i = 0; i <= N; i++) tree.add(new ArrayList<>());

        for(int i = 1; i <= N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                if(str.charAt(j) == 'Y') {
                    tree.get(i).add(j + 1);
                }
            }
        }

        int max = 0;

        for(int i = 1; i <= N; i++) {
            boolean[] chk = new boolean[N + 1]; // 친구 인지 아닌지
            chk[i] = true;

            int cnt = 0;

            for(int friend : tree.get(i)) { // 1: 바로 친구
                if(!chk[friend]) {
                    chk[friend] = true;
                    cnt++;
                }
            }

            for(int friend : tree.get(i)) { // 2: 건너 친구
                for(int hover : tree.get(friend)) {
                    if(!chk[hover]) {
                        chk[hover] = true;
                        cnt++;
                    }
                }
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max); // 시간복잡도가 너무 큰거 같은데... 다른 방법으로 나중에 풀어봐야될듯
    }

}
