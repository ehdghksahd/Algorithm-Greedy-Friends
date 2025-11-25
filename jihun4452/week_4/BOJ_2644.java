import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		//여러 사람 주어지고, 몇촌인지만 구하면 끝남.
		//전체 사람 수 N, 둘째줄부터 촌수 계산 서로 다른 번호 주어짐,
		//셋줄 부터, 부모, 자식간의 관계 개수 M 주어진다.
		//넷부터, 부모 자식간 관계 나타내는 번호 x, y 나옴
		//촌수 계산 불가 -1, 아니면 촌수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 전체 사람의 수

		StringTokenizer st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());

		int m = Integer.parseInt(br.readLine());

		List<Integer>[] graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());

			//부모 자식 그래프 배열에 넣음
			graph[parent].add(child);
			graph[child].add(parent);
		}

		int[] dist = new int[n+1];
		//dist로 모든 사람의 수를 -1로 해놓음, 촌수 못찾으면 -1 이니까
		Arrays.fill(dist, -1);
		Queue<Integer> q = new LinkedList<>();
		q.add(first);
		dist[first] = 0; //시작은 0 시작 사람의 촌수는 0이어야함

		while(!q.isEmpty()){
			int current = q.poll(); //큐 빌때까지 사람 꺼내서 본다.
			for(int next: graph[current]){ //current 2, 라면 1 7 8 9 , 들어가고 방문 안했는지 확인함 -1 이면 방문 안한거
				if (dist[next] == -1){
					dist[next] = dist[current] +1; //di[2 ] = di[7] + 1 = 1, 그러고 다음 큐는 2가 들어감 반복,
					q.add(next); //다음 사람 큐에 넣음, 방문 안한사람만 if 걸림
				}
			}
		}
		System.out.println(dist[second]);
	}
}
