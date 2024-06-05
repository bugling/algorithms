package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 백준 온라인 저지 11724번
 * 
 * 
 * [ 노드 구성 ]
 * 1 ---> 2      
 * |     / 
 * |   /  
 * ▽ ↙ 
 * 5
 * 
 * 3 -> 4 -> 6 
 * 
 * 
 * [ 인접 노드 정리 ]
 * 1 -> 2, 5
 * 2 -> 1, 5
 * 3 -> 4
 * 4 -> 3, 6
 * 5 -> 1, 2
 * 6 -> 4	
 *	
 *	
 * [ 설명 ]
 * 각 숫자가 연결된 그래프가 있다. 그래프의 연결 요소의 개수를 구한다.(DFS실행 횟수)
 * 
 * 
 * [ 슈도코딩 ]
 * 
 * n: 노드 개수 
 * m: 에지 개수
 * A: 그래프 데이터 저장 인접 그래프
 * visited: 방문 기록 저장 배열
 * 
 * for(n의 개수만큼 반복하기) {
 * 		A 인접 리스트의 각 ArrayList 초기화하기
 * }
 * 
 * for(m의 개수만큼 반복하기) {
 * 		A  인접 리스트에 그래프 데이터 저장하기
 * }
 * 
 * for(n의 개수만큼 반복하기) {
 * 		if(방문하지 않은 노드가 있다면) {
 * 			연결 요소 개수++
 * 
 * 			DFS 실행하기
 * 		}
 * }
 * 
 * DFS {
 * 		if(현재 노드 == 방문 노드) return;
 * 		visited 배열에 현재 노드 방문 기록하기
 * 		현재 노드의 연결 노드 중 방문하지 ㅇ낳은 노드로 DFS 실행하기(재귀 함구 형태)
 * }
 * 
 * 
 * [ 입력값 ]
6 5
1 2
2 5
5 1
3 4
4 6
	
	[ 결과 ]
	2

 * 
 * **/
public class 연결요소개수구하기_DFS_Practice { 			// Depht First Serach

	//==============================================
	// static 자료구조
	//==============================================
	static ArrayList<Integer>[] A;
	static boolean visited[];
	
	//==============================================
	// 로직 실행 메인 메서드
	//==============================================
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());		// 노드 개수
		int m = Integer.parseInt(st.nextToken());		// 에지 개수
		
		A = new ArrayList[n+1];							// 그래프 데이터 저장 인접 그래프
		visited = new boolean[n+1];						// 방문 기록 저장 배열
		
		for(int i=1; i<n+1; i++) {						// 인접 리스트 초기화하기
			A[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {						// 엣지의 수만큼 반복
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			/**
			 * ex)
			 * 
			 * 1 -> 2
			 * |
			 * ▽
			 * 3
			 * 
			 * m = 0
			 * 입력값: s = 1 / e = 2
			 * 
			 * A[1] = {2}
			 * A[2] = {1}
			 * 
			 * m = 1
			 * 입력값: s = 1 / e = 3
			 * 
			 * A[1] = {2,3}
			 * A[2] = {1}
			 * A[3] = {1}
			 * 
			 **/
			A[s].add(e);	// 양방향 에지이므로 양쪽에 에지를 더하기
			A[e].add(s);
		}
		
		int count = 0;
		for(int i = 1; i<n+1; i++) {	// 노드의 개수만큼 반복해서 깊이우선 탐색 수행
			if(visited[i] == false) {
				count++;
				DFS(i);
			}
		}
		
		System.out.println(count);
	}

	//==============================================
	// 깊이 우선 탐색 메서드
	// - 방문기록 체크
	//==============================================
	static void DFS(int v) {
		
		// 한 번 방문한 적이 있으면 메서드 종료
		if(visited[v]) return;
		
		// 방문한 적이 없다면 해당 부분 true로 표시
		visited[v] = true;
		
		// A의 요소들을 하나씩 꺼내서 방문한 적이 없다면 재귀적으로 DFS 호출
		for(int i : A[v]) {	 
			if(visited[i] == false) {		// 연결 노드 중 방문하지 않았던 노드만 탐색하기
				DFS(i);
			}
		}
		
	}
	
}
