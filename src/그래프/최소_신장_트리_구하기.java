package 그래프;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 백준 1197
 * 
 * [ 문제 ]
 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
 * 최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
 * 
 * [ 입력 ]
 * 첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
 * 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
 * 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
 * 그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다.
 * 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.
 * 
 * ( 예제 입력 )
3 3
1 2 1
2 3 2
1 3 3
 * 
 * [ 출력 ]
 * 첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.
 * 
 * ( 예제 출력 )
 * 3
 * 
 * [ 설명 ]
 * 1. 에지 리스트에 에지 정보를 저장한 후 부모 노드 데이터를 초기화한다.
 * 	  최소 신장 트린는 에지 중심의 알고리즘이므로 데이터를 에지 리스트를 활용해 저장해야 한다.
 * 	  사이클 생성 유무를 판단하기 위해 유니온 파인드용 부모 노드도 초기화한다.
 *
 * PriorityQueue<pEdge> queue		//  우선순위 큐를 사용해서 자동 정렬(가중치를 기준)
 * Edge			|	1	2	3	
 * ----------------------------
 * Node s		|	1	2	1	
 * Node e		|	2	3	2	
 * Weight (v)	|	1	2	3	
 * 
 * parent
 * 1	2	3
 * ------------
 * 1	2	3
 * 
 * 2. 크루스칼 알고리즘을 수행한다.
 * 	  현재 미사용 에지 중 가중치가 가장 작은 에지를 선택하고, 이 에지를 연결했을 때 사이클의 발생 유무를 판단한다.
 *	  사이클이 발생하면 생략하고, 발생하지 않으면 에지값을 더한다.
 * 
 * 3. 과정 2에서 에지를 더한 횟수가 'V(노드 개수)-1'될 때 까지 반복하고, 반복이 끝나면 에지의 가중치를 모두 더한 값을 출력한다.
 * 
 * [ 슈도 코딩 ]
 * 
 * N: 노드 수 		M: 에지 수
 * parent: 대표 노드 저장 배열
 * queue: 에지 정보를 저장할 우선순위 큐
 * 
 * for(M만큼 반복하기) {
 * 		queue에 에지 정보 저장하기
 * }
 * 
 * while(사용한 에지 수가 노드 -1이 될 때까지) {
 * 		큐에서 에지 정보 가져오기
 * 		에지 시작점과 끝점의 부모 노드가 다르면 -> 연결해도 사이클이 생기지 않으면
 * 		union 연산 수행하기
 * 		에지의 가중치를 정답 변수에 더하기
 * }
 * 
 * 정답 변수 출력하기
 * 
 * union(a, b) {
 * 		a와 b의 대표 노드 찾기
 * 		두 원소의 대표 노드끼리 연결하기
 * }
 * 
 * find(a) {
 * 		a가 대표 노드면 리턴하기
 * 		아니면 a의 대표 노드값을 find(parent[a]) 값으로 저장 -> 재귀 함수 형태
 * }
 * 
 * pNode implements Comparable {
 * 		s: 출발 노드, e: 종료 노드, v: 가중치
 * 		가중치의 오름차순 정렬되도록 compareTo 함수 구현하기
 * }
 * 
 * **/
public class 최소_신장_트리_구하기 {

	static int[] parent;
	static PriorityQueue<pEdge> queue;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		queue = new PriorityQueue<>();		// 자동 정렬을 위해 우선순위 큐 자료구조 선택하기
		parent = new int[N+1];
		
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int v = sc.nextInt();
			
			queue.add(new pEdge(s, e, v));		// 모든 에지 정보를 담은 queue(가중치 오름차순으로 정렬되어 있음)
		}

		// 모든 노드가 연결되지 않으면 MST알고리즘이 유효하지 않다고 GPT행님이 그럤음. 그런 경우는 생각 안하고 문제 풀어도 되는 듯!
		
		int useEdge = 0;
		int result = 0;
		while(useEdge < N-1) {		// 모든 노드가 사이클 없이 연결되는 에지의 수
			pEdge now = queue.poll();
			if(find(now.s) != find(now.e)) {		// 같은 부모가 아니라면 연결해도 사이클이 생기지 않음
				union(now.s, now.e);
				result = result + now.v;
				useEdge++;
			}
		}
		
		System.out.println(result);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			parent[b] = a;
		}
	}
	
	public static int find(int a) {
		if(a == parent[a])
			return a;
		else 
			return parent[a] = find(parent[a]);
	}

}

class pEdge implements Comparable<pEdge> {

	int s, e, v;
	
	pEdge(int s, int e, int v) {
		this.s = s;
		this.e = e;
		this.v = v;
	}
	
	@Override
	public int compareTo(pEdge o) {
		// 가중치를 기준으로 오름차순 정렬을 하기 위해 compareTo 재정의하기
		return this.v - o.v;
	}
	
	
}
