import java.util.*;
import java.io.*;

public class Main{
    public static int func(int N) {
        if(N  == 3) return 4; // 3 일 때 {1, 1, 1}, {1, 2}, {2, 1}, {3} -> 4가지
        if(N == 2) return 2; // 2 일 때 {1, 1}, {2} -> 2가지
        if(N == 1) return 1; // 1 일 때 {1} -> 1가지

        return func(N - 1) + func(N - 2) + func(N - 3);
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(func(N));
        }

    }
}
