package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2178번 문제
 * 
 * [ 설명 ]
 * 1과 0으로 이루어진 미로가 있다. 1은 길, 0은 벽을 이다.
 * 0,0좌표에서 1만 지나서 N,M좌표까지 가는 최단 경로를 구해라 
 * 
 * [ 예시 ]
 * N: 4, M: 6
 * 
 * (탐색전)
101111
101010
101011
111011

1 0 1 1 1 1 
1 0 1 0 1 0 
1 0 1 0 1 1 
1 1 1 0 1 1 

 * 
 * (탐색후 (공백은 구분을 위해 넣음))
 * 1 0 9 10 11 12 
 * 2 0 8 0  12 0
 * 3 0 7 0  13 14
 * 4 5 6 0  14 15
 * 
 * 
 * [ 슈도코딩 ]
 * dx, dy: 상하좌우를 탐색하기 위한 define값 정의 변수
 * A: 데이터 저장 2차원 행렬
 * N: row수, M: column수
 * visited: 방문 기록 저장 배열
 * 
 * A 배열 초기화 하기
 * visited 배열 초기화하기
 * for(N의 개수만큼 반복하기) {
 * 		for(M의 개수만큼 반복하기) {
 * 			A 배열에 데이터 저장하기
 * 		}
 * }
 * 
 * BFS(0, 0) 실행하기
 * A[N-1][M-1] 값 출력하기
 * 
 * BFS {	// BFS 구현하기
 * 		큐 자료구조에 시작 노드 삽입하기(add연산)
 * 		visited배열에 현재 노드 방문 기록하기
 * 		
 * 		while(큐에 비어 있을 때까지) {
 * 			큐에서 노드 데이터를 가져오기(poll 연산)
 * 			for(상하좌우 탐색){
 * 				if(유효한 좌표){
 * 					if(이동할 수 있는 칸이면서 방문하지 않은 노드){
 * 						visited 배열에 방문 기록하기
 * 						A배열에 depth를 현재 노드의 depth + 1로 업데이트 하기
 * 						큐에 데이터 삽입하기 
 * 					}
 * 				}
 * 			}
 * 		}
 * }
 * 
 * **/
public class 미로탐색하기_BFS_Practice {
					// 위 오른  아래 왼
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] visited;
	static int[][] A;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		visited = new boolean[N][M];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();			// 1011010101
			for(int j=0; j<M; j++) {
				A[i][j] = Integer.parseInt(line.substring(j, j+1));		// line을 잘라서 칸에 하나씩 넣음 
			}
		}
		
		BFS(0, 0);
		System.out.println(A[N-1][M-1]);
	}

	private static void BFS(int i, int j) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {i, j});
		
		visited[i][j] = true;
		// 포인터가 이동할 때 마다 queue에 좌표가 저장되고
		// poll을 이용해서 좌표값을 꺼내쓰기 때문에
		// queue가 비는 시점은 포인터가 이동하지 못하는 모서리 지점이다.
		while(!queue.isEmpty()) {			
			
			int now[] = queue.poll();		// 현재 좌표 [x, y]
			
			/*
			 * static int[] dx = {0, 1, 0, -1};
			 * static int[] dy = {1, 0, -1, 0};
			 * */
			for(int k=0; k<4; k++) {
				
				// k=0: 위	// k=1: 오른쪽	  // k=2: 아래   //k=3: 왼
				int x = now[0] + dx[k];			// now[0]: x좌표 담당
				int y = now[1] + dy[k];			// now[1]: y좌표 담당
				
				if(x >= 0 && y >=0 && x < N && y < M) {		// 포인터가 2차원 배열을 넘어가면 안됨
					if(A[x][y] != 0 && !visited[x][y]) {
						// 이제 갈 수 있는 곳이다!
						visited[x][y] = true;				// 방문 체크
						A[x][y] = A[now[0]][now[1]] + 1;	// 기존 depth에 +1 값을 넣어줌		**A[now[0]][now[1]]: 기존에 포커스가 위치하던 곳
						
						int[] temp = new int[] {x, y};
						queue.add(temp);
					}
				}
			}
		}
		
	}

}
