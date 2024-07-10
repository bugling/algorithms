package 그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 백준 1916
 * 
 * [ 문제 ]
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다.
 * 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
 * A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
 * 
 * [ 입력 ]
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
 * 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다.
 * 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
 * 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다.
 * 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 * 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.
 * 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
 * 
 * ( 입력 예제 )
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
 * 
 * [ 출력 ]
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 * 
 * ( 출력 예제 )
 * 4
 * 
 * [ 설명 ]
 * 시작점과 도착점이 주어지고, 이 목적지까지 가는 최소 비용(최단 거리)를 구하는 문제이다.
 * 또한 버스 비용의 범위가 음수가 아니기 때문에 이 문제는 다익스트라 알고리즘을 이요해 해결할 수 있다.
 * 도시의 개수가 최대 1,000개이므로 인접 행렬 방식으로도 그래프를 표현할 수 있지만,
 * 시간 복잡도나 공간 효율성 측면을 고래해 인접 리스트의 자료구조를 선택했다.
 * 
 * 
 * 1. 첫째 숫자(도시 개수)의 크기만큼 인접 리스트 배열의 크기를 설정한다.
 * 	  이때 버스의 비용(가중치)이 존재하므로 인접 리스트 배열의 자료형이 될 클래스를 선언한다.
 *    그리고 둘쨰 숫자(버스 개수)의 크기만큼 반복문을 돌면서 그래프를 리스트 배열에 저장한다.
 * 
 * ArrayList<node>[5]
 * 1 -> (2,2) (3,3) (4,1) (5,10)
 * 2 -> (4,2)
 * 3 -> (4,1) (5,1)
 * 4 -> (5,3)
 * 5
 * 
 * 2. 다익스트라 알고리즘을 수행한다. 최단 거리 배열이 완성되면 정답을 출력한다.
 * 
 * [ 슈도 코딩 ]
 * 필요한 자료구조 선언하기(그래프 정보 저장, 최단 거리 저장, 노드 사용 여부 저장)
 * N: 노드 수	M: 에지 수
 * 선언한 변수들을 초기화하기
 * 거리 배열은 충분히 큰 수로 초기화하기
 * for(노드 개수){
 * 		그래프 정보를 저장한느 인접 리스트 초기화하기
 * }
 * for(에지 개수){
 * 		인접 리스트 배열에 이 에지 정보를 저장하기
 * }
 * startIndex: 시작점
 * endIndex: 도착점
 * 시작점을 기준으로 다익스트라 수행
 * 최단 거리 배열에서 목적지 길이를 찾아 출력하기
 * 
 * // 다익스트라(시작점, 종료점) {
 * 		시작점을 오름차순 우선순위 큐에 넣고 시작
 * 		// 자동으로 거리가 최소인 노드를 선택할 수 있게 해 주는 자료구조
 * 		while(큐가 빌 때까지) {
 * 			현재 선택된 노드가 방문한 적이 있는지 확인
 * 			현재 노드를 방문 노드로 업데이트
 * 			for(현재 선택 노드의 에지 개수) {
 * 				if(타깃 노드 방문 전이고 현재 선택 노드 최단 거리 + 비용 < 타깃 노드의 최단 거리이면){
 * 					타깃 노드 최단 거리 업데이트
 * 					우선순위 큐에 타깃 노드 추가
 * 				}
 * 			}
 * 		}
 * }
 * 
 * // 가중치가 있는 그래프를 담기 위한 클래스 별도 구현하기
 * Edge {
 * 		vertex: 가리키는 노드
 * 		value: 에지의 가중치
 * 		
 * 		우선순위 큐 정렬 기준을 위해 compareTo() 함수 구현하기
 * 	}
 * 
 * **/
public class 최소_비용_구하기_다익스르타 {

	static int N, M;
	static ArrayList<Node>[] list;
	static int[] dist;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		dist = new int[N + 1];
		visit = new boolean[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE); 		// 거리 배열을 충분히 큰 수로 초기화하기
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<Node>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start 	= Integer.parseInt(st.nextToken());
			int end 	= Integer.parseInt(st.nextToken());
			int weight 	= Integer.parseInt(st.nextToken());
			list[start].add(new Node(end, weight));
		}
		st = new StringTokenizer(br.readLine());
		
		int startIndex 	= Integer.parseInt(st.nextToken());
		int endIndex 	= Integer.parseInt(st.nextToken());
		bw.write(dijkstra(startIndex, endIndex) + "\n"); 		// 다익스트라 알고리즘 수행하기
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	//=========================================
	// 다익스트라 알고리즘
	//========================================= 
	private static int dijkstra(int start, int end) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node nowNode = pq.poll();
			int now = nowNode.targetNode;
			if(!visit[now]) {
				visit[now] = true;
				for(Node n : list[now]) {		// 미리 계산된 거리 > 여태껏 온 거리 + 앞으로의 비용 을 비교
					if(!visit[n.targetNode] && dist[n.targetNode] > dist[now] + n.value) {
						dist[n.targetNode] = dist[now] + n.value;	// 새로 계산된 값이 최소비용이면 dist[]의 요소 업데이트
						
						// 최소 경로로 지정된 지점에서 연결된 각각의 노드를 다시 계산하기 위해 PriorityQueue자료구조에 넣는다.
						pq.add(new Node(n.targetNode, dist[n.targetNode]));
					}
				}
			}
		}
		
		// start~end까지의 최소 거리가 return 된다.
		return dist[end];
	}

}

class Node implements Comparable<Node> {

	int targetNode;
	int value;
	
	Node(int targetNode, int value){
		this.targetNode = targetNode;
		this.value = value;
	}
	
	@Override
	public int compareTo(Node o) {
		return value - o.value;
	}
	
}
