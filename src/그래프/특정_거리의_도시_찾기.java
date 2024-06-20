package 그래프;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 18352
 * 
 * [ 문제 ]
 * 어떤 나라에는 1번부터 N번까지의 도시와 M개의 단방향 도로가 존재한다. 모든 도로의 거리는 1이다.
 * 이 때 특정한 도시 X로부터 출발하여 도달할 수 있는 모든 도시 중에서, 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하는 프로그램을 작성하시오.
 * 또한 출발 도시 X에서 출발 도시 X로 가는 최단 거리는 항상 0이라고 가정한다.
 * 예를 들어 N=4, K=2, X=1일 때 다음과 같이 그래프가 구성되어 있다고 가정하자.
 *
 * 이 때 1번 도시에서 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 2인 도시는 4번 도시 뿐이다.
 * 2번과 3번 도시의 경우, 최단 거리가 1이기 때문에 출력하지 않는다.
 *
 * [ 그래프 ]
 * 1------->2
 * |      / |
 * |     /  |
 * |    /   |
 * |   /    |
 * v <      v  
 * 3        4
 * 
 * [ 입력 ]
 * 첫째 줄에 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X가 주어진다.
 * (2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N)
 * 둘째 줄부터 M개의 줄에 걸쳐서 두 개의 자연수 A, B가 공백을 기준으로 구분되어 주어진다.
 * 이는 A번 도시에서 B번 도시로 이동하는 단방향 도로가 존재한다는 의미다.
 * (1 ≤ A, B ≤ N) 단, A와 B는 서로 다른 자연수이다.
 * 
 * ( 예제 입력 )
N M K X
--------
4 4 1 1
1 2
1 3
2 3
2 4
 * 
 * 
 * [ 출력 ]
 * X로부터 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 K인 모든 도시의 번호를 한 줄에 하나씩 오름차순으로 출력한다.
 * 이 때 도달할 수 있는 도시 중에서, 최단 거리가 K인 도시가 하나도 존재하지 않으면 -1을 출력한다.
 * 
 * ( 예제 출력)
 * 2
 * 3
 * 
 * [ 설명 ]
 * 모든 도로의 거리가 1이므로 가중치 없는 인접 리스트로 이 그래프를 표현할수 있다.
 * 도시의 개수가 300,000, 도로의 최대 크기가 1,000,000이므로 BFS탐색을 수행하면 이 문제를 시간 복잡도 안에서 해결할 수 있다.
 * 
 * 1. 인접 리스트로 도시와 도로 데이터의 그래프를 구현한다.
 * 		ArrayList<Integer>[N]로 저장
 * 		List 1 -> 2,3
 *  		 2 -> 3,4
 *  	     3
 *  		 4 
 * 
 * 2. BFS 탐색 알고리즘으로 탐색을 수행하면서 각 도시로 가는 최단 거릿값을 방문 배열에 저장한다.
 * 3. 탐색 종료 후 방문 배열에 값이 K와 같은 도식의 번호를 출력한다.
 * 
 * [ 슈도 코딩 ]
 * N: 노드 개수		M: 에지 개수	K: 목표 거리	X: 시작점
 * answer: 정답 리스트
 * A: 그래프 데이터 저장 인접 리스트
 * visited: 방문 거리 저장 배열
 * 
 * for(N의 개수만큼 반복하기)
 * {
 * 		A 인접 리스트의 각 ArrayList 초기화하기
 * }
 * for(M의 개수만큼 반복하기)
 * {
 * 		A 인접 리스트에 그래프 데이터 저장하기
 * }
 * visited 배열 초기화하기
 * BFS(X) 실행하기
 * for(N의 개수만큼 반복하기)
 * {
 * 		방문 거리가 K인 노드의 숫자를 정답 배열에 더하기
 * }
 * 정답 배열 오름차순 정렬해 출력하기
 * 
 * BFS {
 * 		큐 자료구조에 출발 노드 더하기(add 연산)
 * 		visited 배열에 현재 노드 방문 기록하기
 * 		while(큐가 빌 때까지) {
 * 			큐에서 노드 데이터를 가져오기(poll 연산)
 * 			가져온 노드 출력하기
 * 			현재 노드의 연결 노드 중 방문하지 않은 노드로
 * 			큐에 데이터 삽입(add 연산)하고 visited 배열에 방문 거리 기록하기
 * 			-> 이전 노드의 방문 노드 거리 + 1
 * 		}
 * }
 * 
 * 
 * **/
public class 특정_거리의_도시_찾기 {
	
	static int visited[];				// 방문 거리 저장 배열
	static ArrayList<Integer>[] A;		// 그래프 데이터 저장 인접 리스트
	static int N, M, K, X;				// N: 노드 개수		M: 에지 개수	K: 목표 거리	X: 시작점
	static List<Integer> answer;		// 정답 리스트
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		K = scan.nextInt();
		X = scan.nextInt();
		A = new ArrayList[N + 1];
		answer = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			A[i] = new ArrayList<Integer>();
		}
		
		/**
		 * A		// [null, [2, 3], [3, 4], [], []]
		 * 
		 * [null, [2, 3], [3, 4], [], []]
		 * 
		 * 1 -> 2,3
		 * 2 -> 3,4
		 * 3
		 * 4
		 * **/
		for(int i=0; i<M; i++) {
			int S = scan.nextInt();
			int E = scan.nextInt();
			A[S].add(E);
		}
		
		visited = new int[N + 1];
		
		for(int i=0; i<=N; i++) {
			visited[i] = -1;
		}
		
		BFS(X);
		
		// K와 동일한 거리를 가진 지점이 있다면 answer에 담는다.
		for(int i=0; i<=N; i++) {
			if(visited[i] == K) {
				answer.add(i);
			}
		}
		
		if(answer.isEmpty()) {
			System.out.println("-1");
		}
		else {
			Collections.sort(answer);
			for(int temp : answer) {
				System.out.println(temp);
			}
		}
		
	}

	// 너비 우선 탐색
	private static void BFS(int Node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		// 최초로 BFS에 진입한 X에 대한 연산
		queue.add(Node);
		visited[Node]++;
		
		// queue가 비어있지 않다면 계속 진행
		while(!queue.isEmpty()) {
			int now_Node = queue.poll();
			
			for(int i : A[now_Node]) {
				if(visited[i] == -1) {
					visited[i] = visited[now_Node] + 1;
					queue.add(i);
				}
			}
		}
		
	}
	
}
