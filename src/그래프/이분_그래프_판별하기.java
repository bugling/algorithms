package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 백준 1707
 * 
 * [ 문제 ]
 * 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때,
 * 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.
 * 그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다.
 * 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다.
 * 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데,
 * 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다. 
 * 
 * ( 예제 입력 )
2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2

 * 
 * [ 출력 ]
 * K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.
 * 
 * ( 예제 출력 )
 * YES
 * NO
 * 
 * [ 제한 ]
 * - 2 ≤ K ≤ 5
 * - 1 ≤ V ≤ 20,000
 * - 1 ≤ E ≤ 200,000
 * 
 * [ 설명 ]
 * 사이클이 발생하지 않으면 탐색을 하면서 다음 노드를 이번 노드와 다른 집합으로 지정한다.
 * 단, 사이클이 발생했을 때는 이런 이분 그래프가 불가능할 때가 있다.
 * 
 * 이분 그래프 불가능 예시)
 * 
 * 1 -> 2
 * ㅅ  /
 * | /
 * |/
 * 3
 * 
 * 기존의 탐색 메커니즘에서 탐색한 노드에 다시 접근하게 됐을 때 현재 노드의 집합과 같으면 이분 그래프가 불가능한 것으로 판별할 수 있다.
 * 
 * 1. 입력된 그래프 데이터를 인접 리스트로 구현한다
 * 
 * 1--> 2		1 -> 2
 *    /ㅅ		2 -> 3
 *   / |		3 -> 4
 *  /  |		4 -> 2
 * 3-->4		
 * 
 * 2. 모든 노드로 각각 DFS 탐색 알고리즘을 적용해 탐색을 수행한다.
 *    DFS를 실행할 때 현재 노드에서 연결된 노드 중 이미 방문한 노드가 나와 같은 집합이면 이분 그래프가 아닌 것으로 판별한다.
 *    실행 결과가 이분 그래프가 아니면 이후 노드는 탐색하지 않는다.
 *    
 * 3. 이분 그래프 여부를 정답으로 출력한다.
 * 		ex) 출발 노드 (4)와 도착 노드(2)의 집합이 같으므로 이분 그래프 불가능 => NO 출력
 * 
 * 4. 사례의 개수만큼 1~3을 반복한다.
 * 
 * 	** 모든 노드로 DFS를 실행하는 이유: 그래프의 모든 노드가 이어져 있지 않고, 여러 개의 부분 그래프로 이뤄진 케이스가 존재할 수 있기 때문
 * 
 * 
 * [ 슈도 코딩 ]
 * check: 이분 그래프 체크 배열
 * A: 그래프 데이터 저장 인접 리스트 	visited: 방문 기록 저장 배열
 * N: 테스트 케이스
 * 
 * for(N의 개수만큼 반복하기)
 * {
 * 		V: 노드 개수
 * 		E: 에지 개수
 * 		for(V의 개수만큼 반복하기) {
 * 			A 인접 리스트의 각 ArrayList 초기화하기
 * 		}
 * 		for(E의 개수만큼 반복하기) {
 * 			A 인접 리스트에 그래프 데이터 저장하기
 * 		}
 * 		for(V의 개수만큼 반복하기) {
 * 			각 노드에서 DFS 실행 -> 결과가 이분 그래프가 아니면 반복 종료
 * 		}	
 * 		이분 그래프 여부를 정답으로 출력하기
 * }
 * 
 * DFS {
 * 		visited 배열에 현재 노드 방문 기록하기
 * 	
 * 		if(현재 노드의 연결 노드 중 방문하지 않는 노드로) {
 * 			현재 노드와 다른 집합으로 연결 노드 집합 저장하기
 * 			DFS 실행하기 (재귀 형태)
 * 		}
 * 		else {		// 이미 방문한 노드인데, 현재 나의 노드와 같은 집합이면
 * 			이분 그래프가 아님
 * 		}
 * 	
 * }
 * 
 * **/
public class 이분_그래프_판별하기 {

	static ArrayList<Integer>[] A;
	static int[] check;
	static boolean visited[];
	static boolean IsEven;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
			
			String[] s = br.readLine().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			
			A = new ArrayList[V + 1];
			visited = new boolean[V + 1];
			check = new int[V + 1];
			
			IsEven = true;
			
			for(int i=1; i<=V; i++) {
				A[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<E; i++) {
				s = br.readLine().split(" ");
				int Start = Integer.parseInt(s[0]);
				int End   = Integer.parseInt(s[1]);
				
				A[Start].add(End);
				A[End].add(Start);
			}
			
			// 주어진 그래프가 1개로 연결돼 있다는 보장이 없으므로 모든 노드에서 수행하기
			for(int i=1; i<=V; i++) {
				if(IsEven)
					DFS(i);
				else 
					break;
			}
			
			if(IsEven)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	
	private static void DFS(int node) {
		
		// DFS에 진입한 요소의 방문표시를 체크함
		visited[node] = true;
		
		// index가 key가 되어 내용을 node번에 위치한 요소를 하나씩 꺼냄
		// for문을 다시 탔다는 건 이전의 분기를 모두 탐색했다는 뜻! i에 해당하는 ArrrayList를 모두 재귀호출하고 끝냈기 때문임.
		for(int i : A[node]) {		
			if(!visited[i]) {
				// 인접한 노드는 같인 집합이 아니므로 이전 노드와 다른 집합으로 처리하기
				check[i] = (check[node] + 1) % 2; 		// toggle기능이라고 보면 됨!	// node == 0 -> 1		// node == 1 -> 0
			
				// DFS의 핵심은 '요소'가 'key'가 되어 다시 요소를 꺼내는 것 같음.
				DFS(i);
			}
			// 이미 방문한 노드가 현재 내 노드와 같은 집합이면 이분 그래프가 아님
			else if(check[node] == check[i]) {		// node와 i는 연결되어 있는 요소들	// 이 두 check값이 같다면 이분 그래프가 성립되지 않음
				// else{	if(check[node] == check[i])	}	와 동일한 로직으로 봐야 할 듯
				
				IsEven = false;
			}
		}
	}

}
 