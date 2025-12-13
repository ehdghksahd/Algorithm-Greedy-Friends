import java.util.*;
import java.io.*;

// 퀸 십자 + 대각

public class Main{
    private static int[][] board;
    private static int N;
    private static int cnt = 0;
    private static void solve(int row) {
        if(row == N) {
            cnt++;
            return;
        }

        for(int i = 0; i < N; i++) {
            if(chk(row, i)) {
                board[row][i] = 1;

                solve(row + 1);

                board[row][i] = 0;
            }

        }

    }
    private static boolean chk(int r, int c) {
        for(int i = 0; i < r; i++) {

            if(board[i][c] == 1) return false;

            int tmp = r - i;

            if(c - tmp >=0 && board[i][c - tmp] == 1) return false;
            if(c + tmp < N && board[i][c + tmp] == 1) return false;

        }
        return true;
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        solve(0);
        System.out.println(cnt);

    }
}
