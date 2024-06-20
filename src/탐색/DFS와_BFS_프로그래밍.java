package 탐색;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * [ 그래프 ]
 * 1 ------ 2
 * | \    /
 * |  \  /
 * |   \
 * | /  \   
 * 4------- 3 
 * 
 * [ 인접 리스트 ]
 * 1 -> 2, 3, 4
 * 2 -> 4, 1
 * 3 -> 4, 1
 * 4 -> 1, 2, 3
 * 
 * [ 슈도 코딩 ]
 * 
 * N: 노드 개수
 * M: 에지 개수
 * Start: 시작점
 * A: 그래프 데이터 저장 인접 리스트
 * visited: 방문 기록 저장 배열
 * 
 * for(N의 개수만큼 반복하기){
 * 		A인접 리스트에 그래프 데이터 저장하기
 * }
 * 
 * // 방문할 수 있는 노드가 여러 개일 경우에는 번호가 작은 것을 먼저 방문하기 위해 정렬하기
 * for(N의 개수만큼 반복하기){
 * 		각 노드와 관련된 에지를 정렬하기
 * }
 * 
 * visitied 배열 초기화하기
 * DFS(Start) 실행하기
 * visited 배열 초기화하기
 * BFS(Start) 실행하기
 * 
 * DFS {
 * 		현재 노드 출력하기
 * 		visited 배열에 현재 노드 방문 기록하기
 * 		현재 노드의 연결 노드 중 방문하지 않은 노드로 DFS 실행하기(재귀 함수 형태)
 * }
 * 
 * BFS {
 * 		큐 자료구조에 시작 노드 삽입하기(add 연산)
 * 		visitied 배열에 현재 노드 방문 기록하기
 * 		while(큐가 비어 있을 때까지) {
 * 			큐에서 노드 데이터를 가져오기(poll 연산)
 * 			가져온 노드 출력하기
 * 			현재 노드의 연결 노드 중 미방문 노드를 큐에 삽입(add 연산)하고 방문 배열에 기록하기
 * 		}
 * }
 * 
 * 
 * **/
public class DFS와_BFS_프로그래밍 {

	static boolean visited[];
	static ArrayList<Integer>[] A;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	
		int N 		 = scan.nextInt();			// 노드 개수
		int M 		 = scan.nextInt();			// 엣지 개수
		int Start 	 = scan.nextInt();			// 시작점
		
		A = new ArrayList[N + 1];
		
		for(int i=1; i<=N; i++) {
			A[i] = new ArrayList<Integer>();
		}
			
		for(int i=0; i<M; i++) {
			int S = scan.nextInt();
			int E = scan.nextInt();
			A[S].add(E);
			A[E].add(S);
		}
		
//		System.out.println("Sort 전!!");
//		for(int i=1; i<=N; i++) {
//			ArrayList tempList = A[i];
//			for(int j=0; j<tempList.size(); j++) {
//				int tempNum = (int) tempList.get(j);
//				System.out.print(tempNum + "  ");
//			}
//			System.out.println();
//		}		
			
		
		// 번호가 작은 것을 먼저 방무하기 위해 정렬하기
		for(int i=1; i<=N; i++) {
			Collections.sort(A[i]);
			
		}
		
		System.out.println("Sort 후!!");
		for(int i=1; i<=N; i++) {
			ArrayList tempList = A[i];
			for(int j=0; j<tempList.size(); j++) {
				int tempNum = (int) tempList.get(j);
				System.out.print(tempNum + "  ");
			}
			System.out.println();
		}		
				
		
//		visited = new boolean[N+1];
//		DFS(Start);
		
		System.out.println();
		
		visited = new boolean[N + 1];
		BFS(Start);
		System.out.println();
		
	}
	
	private static void DFS(int Node) {
		System.out.print(Node + "  ");
		visited[Node] = true;
		
		for(int i : A[Node]) {
			if(!visited[i]) {
				DFS(i);
			}
		}
	}

	private static void BFS(int Node) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(Node);
		visited[Node] = true;
		
		while(!queue.isEmpty()) {
			int now_Node = queue.poll();
			System.out.println(now_Node + " ");
			
			for(int i : A[now_Node]) {
				if(!visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		
	}

}
