package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 연결요소개수구하기_따라치기 {
	
	//==============================================
	// static 자료구조
	//==============================================
	static ArrayList<Integer>[] A;
	static boolean[] visited;
	
	//==============================================
	// 로직 실행 메인 메서드
	//==============================================
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());		// 노드의 개수
		int m = Integer.parseInt(st.nextToken());		// 엣지의 개수
		
		A = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		
		for(int i=1; i<n+1; i++) {
			visited[i] = false;
			A[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			// 각 숫자를 상호간에 연결시킴
			A[s].add(e);
			A[e].add(s);
		}
		
		int count = 0;
		for(int i=1; i<n+1; i++) {
			if(visited[i] == false) {
				count++;
				
				DFS(i);
			}
		}
		
		System.out.println(count);
		
	}

	//==============================================
	// Depth First Search
	// 	각 숫자를 방문해서 체크표시하기(연결된 노드가 있다면 재기적으로 실행)
	//==============================================
	private static void DFS(int v) {
		
		if(visited[v] == true) return;
		
		visited[v] = true;			// 방문 기록 체크
		for(int i : A[v]) {			// 연결된 노드들을 순차작으로 방문
			DFS(i);
		}
		
	}

}
