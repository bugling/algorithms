package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * [ 문제 ]
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.
 * 단, 모든 간선의 가중치는 10 이하의 자연수이다.
 * 
 * [ 입력 ]
 * 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다.
 * (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다.
 * 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다.
 * 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다.
 * 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다.
 * 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
 * 
 * (입력 예제)
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
 *
 * [ 출력 ]
 * 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
 * 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
 * (예제 출력)
 * 0
 * 2
 * 3
 * 7
 * INF
 * 
 * [ 슈도 코딩 ]
 * 자료구조 선언하기(V,E: 그래프 정보 저장, distance: 최단 거리 저장, visited: 노드 사용 여부 저장)
 * V: 노드 개수
 * E: 에지 개수
 * K: 출발 노드
 * 거리 배열은 충분히 큰 수로 초기화하기
 * for(노드 수){
 * 		그래프 정보를 저장하는 인접 리스트 초기화하기
 * }
 * for(에지 개수){
 * 		인접 리스트 배열에 이 에지 정보를 저장하기
 * }
 * 다익스트라 알고리즘 수행하기
 * 출발 노드는 우선순위 큐에 넣고 시작하기	// 자동으로 거리가 최소인 노드를 선택하게 함
 * while(큐가 빌 떄까지) {
 * 		현재 선택된 노드가 방문된 적이 있는지 확인하기
 * 		현재 노드를 방문 노드로 업데이트하기
 * 		for(현재 선택 노드의 에지 개수) {
 * 			if(타깃 노드 방문 전 && 현재 선택 노드 최단 거리 + 비용 < 타깃 노드의 최단 거리){
 * 				타깃 노드 최단 거리 업데이트하기
 * 				우선순위 큐에 타깃 노드 추가하기
 * 			}
 * 		}
 * }
 * 완성된 거리 배열을 탐색해 출력하기
 * 
 * // 가중치가 있는 그래프를 담기 위한 클래스 별도 구현하기
 * Edge {
 * 		vertex: 가리키는 노드
 * 		value: 에지의 가중치
 * 		
 * 		우선순위 큐 정렬 기준을 위해 compareTo 함수 구현하기
 * }
 * 
 * **/
public class 최단_경로_구하기_다익스트라 {
	
  public static int V,E,K;
  public static int distance[];
  public static boolean visited[];
  public static ArrayList<Edge> list[];
  public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();
  
  public static void main(String[] args) throws IOException {
	  
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(br.readLine());
    distance 	= new int[V + 1];			// 시작점에서 부터의 누적거리를 담을 배열(초기값: 말도 안되게 큰 수)
    visited 	= new boolean[V + 1];		// 방문 여부 확인 배열
    list 		= new ArrayList[V + 1];		// index를 key로 가진 각 노드의 연결 노드(인접 리스트)
    
    for (int i = 1; i <= V; i++) {
      list[i] = new ArrayList<Edge>();
    }
    for (int i = 0; i <= V; i++) {
      distance[i] = Integer.MAX_VALUE;
    }
    
    for (int i = 0; i < E; i++) { // 가중치가 있는 인접 리스트 초기화
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      list[u].add(new Edge(v, w));		// v: 연결된 노드	// w: 가중치
    }
    
    // 각 노드와 시작점에서부터의 거리를 담을 예정
    // 시작점에서부터 연결되지 않은 노드는 담기지 않음(단방향임!!)
    q.add(new Edge(K, 0)); // K를 시작점으로 설정
    
    distance[K] = 0;
    
	while (!q.isEmpty()) {
		Edge current = q.poll();
		int c_v = current.vertex;
		if (visited[c_v]) continue; // 기 방문 노드는 다시 큐에 넣지 않습니다.
		visited[c_v] = true;
		for (int i = 0; i < list[c_v].size(); i++) {
			Edge tmp = list[c_v].get(i);
			int next = tmp.vertex;
			int value = tmp.value;
			
			// 조건문에 만족한다는 건 distance 내부 값이 최대값(2147483647) 이거나,
			// 최소 경로가 아니라는 뜻
			// - next: c_v에 연결되어 있는 노드	- c_v: 현재 노드	- value: c_v ~ next로 가는 가중치
			if (distance[next] > distance[c_v] + value) { // 최소 거리로 업데이트
				// 	시작점에서부터의 누적 거리 = 가중치 + 시작점에서부터의 누적 거리 
				distance[next] = distance[c_v] + value;
				q.add(new Edge(next, distance[next]));
			}
		}
    }
    for (int i = 1; i <= V; i++) { // 거리 배열 출력
	      if (visited[i])
	    	  System.out.println(distance[i]);
	      else
	    	  System.out.println("INF");
	 }
    }
}
class Edge implements Comparable<Edge> {
	
	int vertex, value;
	
	Edge(int vertex, int value) {
		this.vertex = vertex;
		this.value = value;
	}
	
	public int compareTo(Edge e) {
		if (this.value > e.value) return 1;
		else return -1;
	}
}