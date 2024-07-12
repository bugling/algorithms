package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 11657
 * 
 * [ 문제 ]
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다.
 * 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다.
 * 시간 C가 양수가 아닌 경우가 있다.
 * C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.
 * 1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다.
 * 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 
 * 
 * ( 예제 입력 )
3 4
1 2 4
1 3 3
2 3 -1
3 1 -2
 *
 * [ 출력 ]
 * 만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력한다.
 * 그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력한다.
 * 만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력한다.
 * 
 * ( 예제 출력 )
 * 4
 * 3
 * 
 * [ 설명 ]
 * 시작점 및 다른 노드와 관련된 최단 거리를 구하는 문제지만,
 * 특이한 점은 에지에 해당하는 이동하는 시간이 양수가 아닌 0 또는 음수가 가능하다는 것이다.
 * 이렇게 시작점에서 다른 노드와 관련된 최단 거리를 구하는데, 에지가 음수가 가능할 때는 벨만-포드 알고리즘을 사용하면 된다.
 * 
 * 1. 에지 리스트에 에지 데이터를 저장한 후 거리 배열을 다음과 같이 초기화 한다.
 * 	  최초 시작점에 해당하는 거리 배열값은 0으로 초기화한다.
 * 
 * Edge edge[]
 * edge		1		2		3		4
 * ---------------------------------------
 * 시작		1		1		2		3	
 * 종료		2		3		3		1
 * 에제		4		3		-4		-2
 * 
 * distance[N+1]
 * 1		2		3
 * ---------------------
 * 0		무한		무한  
 * 
 * 2. 다음 순서에 따라 벨만-포드 알고리즘을 수행한다.
 * 		1) 모든 에지와 관련된 정보를 가져온 후 다음 조건에 따라 거리 배열의 값을 업데이트한다.
 * 			- 출발 노드가 방문한 적이 없는 노드(출발 노드 거리 == 무한)일 때 값을 업데이트하지 않는다.
 * 			- 출발 노드의 거리 배열값 + 에지 가중치 < 종료 노드의 거리 배열값이 때 종료 노드의 거리 배열값을 업데이트한다.
 * 		2) '노드 개수-1'번 만큼 1)을 반복한다.
 * 		3) 음수 사이클 유무를 알기 위해 모든 에지와 관해 다시 한 번 1)을 수행한다.
 * 			이 때 한 번이라도 값이 업데이트되면 음수 사이클이 존재한다고 판단한다.
 * 
 * 2. 음수 사이클이 존재하면 -1, 존재하지 않으면 거리 배열의 값을 출력한다.
 * 	  단, 거리 배열의 값이 무한일 경우에는 -1을 출력한다.
 * 
 * ** 음수 사이클: 단방향 에지를 따라가다 보면 가중치가 음수인 에지를 거쳐서 이전에 갔던 노드를 재방문하게 되어 사이클이 발생하는 형태.
 * 				음수사이클을 계속 돌다보면 방문 거리가 계속 줄어들어 최소거리를 구할 수 없다고 판단함.
 * 
 * [ 슈도 코딩 ]
 * 자료구조 선언하기(그래프 정보 저장, 최단 거리 저장)
 * N: 노드 개수
 * M: 에지 개수
 * Edges: 에지 리스트 배열
 * 거리 배열은 충분히 큰 수로 초기화하기
 * for(에지 개수){
 * 		에지 리스트 배열에 이 에지 정보를 저장하기
 * }
 *  // 벨만-포드 알고리즘 수행하기
 * 거리 배열에 출발 노드 0으로 초기화하기
 * for(노드 개수 - 1) {
 * 		for(에지 개수) {
 * 			현재 에지 데이터 가져오기
 * 			if(출발 노드가 무한대가 아니라 종료 노드값 < 출발 노드값 + 에지 가중치){
 * 				업데이트 수행 -> 종료 노드값 + 에지 가중치
 * 			}
 * 		}
 * }
 * 
 * for(에지 개수) {		// 음수 사이클 존재 여부 확인하기
 * 		현재 에지 데이터 가져오기
 * 		if(출발 노드가 무한대가 아니라 종료 노드값 < 출발 노드값 + 에지 가중치)
 * 			업데이트 가능 -> 음수 사이클 존재
 * }
 * 
 * 음수 사이클 미존재 -> 거리 배열 출력하기(단, 거리 배열의 값이 무한대일 때 -1 출력)
 * 음수 사이클 존재 -> -1 출력하기
 * 
 *  // 에지를 담기 위한 클래스 별도 구현하기
 *  Edge {
 *  	start(출발 노드)
 *  	end(종료 노드)
 *  	value(에지의 가중치)	
 *  }
 * 
 * 
 **/

public class 타임머신으로_빨리_가기_벨만포드 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;		// 노드 개수, 엣지 개수
	static long distance[];
	static Edge_TimeMerchine edges[];
	
	public static void main(String[] args) throws IOException  {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new Edge_TimeMerchine[M];
		distance = new long[N + 1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);		// 최단 거리 배열 초기화하기
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			edges[i] = new Edge_TimeMerchine(start, end, time);
		}
		
		// 벨만-포드 알고리즘 수행하기
		// 2중 for문으로 로직을 수행하는 이유
		// ( 외부(노드 수), 내부(엣지 수)를 기준으로 반복문을 수행하는 이유 )
		// i가 변할 때 마다 각 노드가 선택되고 j가 변할 때마다 i번째 노드에서 갈 수 있는 각 엣지를 선택해 검사한다.
		distance[1] = 0;
		for(int i = 1; i < N; i++) {		// 노드보다 1개 적은 수만큼 반복하기(사이클을 돌지 않고 다른 노드로 가는 최대 홉 수, 단방향 엣지를 타고 갈 수 있는 마지막에 도착하는 노드는 제외)
			for(int j = 0; j < M; j++) {	// 에지의 개수만큼 반복
				Edge_TimeMerchine edge = edges[j];
				
				// 더 작은 최단 거리가 있을 때 업데이트하기
				if(distance[edge.start] != Integer.MAX_VALUE		// 시작점이 가본 적 없는 곳이라면 검사에서 제외!
						&& distance[edge.end] > distance[edge.start] + edge.time) {
					distance[edge.end] = distance[edge.start] + edge.time;
				}
			}
		}
		
		boolean mCycle = false;		// 음의 사이클 존재 여부를 담는 변수
		
		for(int i = 0; i < M; i++) {	// 음수 사이클 확인하기
			Edge_TimeMerchine edge = edges[i];
			if(distance[edge.start] != Integer.MAX_VALUE
					&& distance[edge.end] > distance[edge.start] + edge.time) {
				mCycle = true;
				break;
			}
		}
		
		// 음의 사이클이 없을 때
		if(!mCycle) {
			for(int i = 2; i <= N; i++) {		// 시작점이 1이기 때문에 2부터 거리 출력
				// 시작점부터 가는 경로가 없는 노드는 -1 출력
				if(distance[i] == Integer.MAX_VALUE) {
					System.out.println("-1");
				} 
				// 경로가 있는 노드는 거리 출력
				else {
					System.out.println(distance[i]);
				}
			}
		} 
		// 음의 사이클이 있을 때 -1 출력		-> 이런 경우 최단 경로를 구할 수 없다고 봄..
		else {
			System.out.println("-1");
		}
	}
}

class Edge_TimeMerchine {
	int start, end, time;
	public Edge_TimeMerchine(int start, int end, int time) {
		this.start 	= start;
		this.end 	= end;
		this.time 	= time;
	}
}
