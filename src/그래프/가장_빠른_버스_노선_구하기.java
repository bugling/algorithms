package 그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 백준 11404
 * 
 * [ 문제 ]
 * n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다.
 * 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 * 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다.
 * 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
 * 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
 * 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다.
 * 시작 도시와 도착 도시가 같은 경우는 없다.
 * 비용은 100,000보다 작거나 같은 자연수이다.
 * 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
 * 
 * ( 입력 예제 )
5
14
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
3 5 10
3 1 8
1 4 2
5 1 7
3 4 2
5 2 4
 * 
 * [ 출력 ]
 * n개의 줄을 출력해야 한다.
 * i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다.
 * 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
 * 
 * ( 출력 예제 )
 * 0 2 3 1 4
 * 12 0 15 2 5
 * 8 5 0 1 1
 * 10 7 13 0 3
 * 7 4 10 6 0
 * 
 * [ 설명 ]
 * 1. 버스 비용 정보를 인접 형렬에 저장한다. 먼저 인접 행령을 초기화한다.
 * 연결 도시가 같으면 (i == j) 0, 아니면 충분히 큰 수로 초기화한다.
 * 그리고 주어진 버스 비용 데이터 값을 인접 형렬에 저장한다.
 * 
 * 2. 플로이드-워셜 알고리즘을 수행한다. 다음 점화식을 활용한 3중 for문으로 모든 중간 경로를 탐색한다.
 * 	플로이드-워셜 점화식
 * 	// 두 도시의 연결 비용 중 최솟값
 * 	distance[S][E] = Math.min(distance[S][E], distance[S][K] + distance[K][E])
 *  
 * 3. 알고리즘으로 변경된 인접 행렬을 출력한다.
 * 인접 행렬 자체가 모든 쌍의 최단 경로를 나타내는 정답 배열이다.
 * 정답 배열을 그대로 출력하되, 문제의 요구사항에 따라 두 도시가 도달하지 못할 때(무한)는 0, 아닐 때는 배열의 값을 출력한다.
 * 
 * [ 슈도 코딩 ]
 * N: 도시 개수		M: 노선 개수
 * distance: 노선 데이터를 저장하는 인접 행렬
 * for(i -> N만큼 반복) {
 * 		for(j- > M만큼 반복){
 * 			시작 도시와 종료 도시가 같으면 0, 아니면 충분히 큰 수로 저장하기
 * 		}
 * }
 * 
 * for(M만큼 반복) {
 * 		노선 데이터를 distance 행렬에 저장하기
 * }
 * 
 * for(k -> N만큼 반복하기) {		// 3중 for문의 순서가 중요함. k가 가장 바깥쪽이 돼야 함
 * 		for(i -> N만큼 반복하기) {
 * 			for(j -> N만큼 반복하기) {
 * 				distance[i][j]에 distance[i][k] + distance[k][j] 값들 중 최솟값 넣기
 * 				i ~ j 사이에 가능한 모든 경로를 탐색하기
 * 			}
 * 		}
 * }
 * 
 * 정답 대병 출려히가 -> 만약 정답 배열의 값이 최초 초기화하기에 충분하기에 충분한 큰 수일 경우에는 도착할 수 없는 경로이기 때문에 0을 출력, 아니면 거리 배열 값 출력
 * 
 * **/
public class 가장_빠른_버스_노선_구하기 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M;
	static int distance[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		distance = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) 
					distance[i][j] = 0;
				else 
					distance[i][j] = 1000001;		// 충분히 큰 수로 저장하기
			}
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(distance[s][e] > v) distance[s][e] = v;		// if문을 성립하는 경우: distance[s][e] 가 Integer.MAX_VALUE인 경우
		}
		
		// 플로이드-워셜 알고리즘 수행하기
		for(int k=1; k<=N; k++) {				// 노드(경유지)별로 모두 검사
			for(int i=1; i<=N; i++) {			// 최단거리 리스트의 y축
				for(int j =1; j<=N; j++) {		// 최단거리 리스트의 x축
					if(distance[i][j] > distance[i][k] + distance[k][j])
						distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(distance[i][j] == 1000001) System.out.print("0 ");
				else System.out.print(distance[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
