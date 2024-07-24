package 트리;

import java.util.Scanner;

/**
 * 
 * 백준 1991
 * 
 * [ 문제 ]
 * 이진 트리를 입력받아 전위 순회(preorder traversal)
 * 				, 중위 순회(inorder traversal)
 * 				, 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.
 * 
 * 			A
 * 		  /	  \	
 * 		B		C
 *   /    	  /	  \
 * D		E		F
 * 					  \
 * 						G
 * 예를 들어 위와 같은 이진 트리가 입력되면,

	전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
	중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
	후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)
	가 된다.
 * 
 * 
 * [ 입력 ]
 * 첫째 줄에는 이진 트리의 노드의 개수 N(1 ≤ N ≤ 26)이 주어진다.
 * 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다.
 * 노드의 이름은 A부터 차례대로 알파벳 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 자식 노드가 없는 경우에는 .으로 표현한다.
 * 
 * ( 예제 입력)
7
A B C
B D .
C E F
E . .
F . G
D . .
G . .
 * 
 * [ 출력 ]
 * 첫째 줄에 전위 순회, 둘째 줄에 중위 순회, 셋째 줄에 후위 순회한 결과를 출력한다. 각 줄에 N개의 알파벳을 공백 없이 출력하면 된다.
 * 
 * ( 예제 출력 )
 * ABDCEFG
 * DBAECFG
 * DBEGFCA
 * 
 * [슈도 코딩]
 * N: 노드 개수
 * tree: tree 데이터 저장 2차원 배열
 * for(N의 개수만큼 반복하기) {
 * 		if(왼쪽 자식 노드가 없을 때) {
 * 			tree 배열에 -1 저장하기
 * 		}else {
 * 			tree 배열에 왼쪽 자식 노드 인덱스 저장하기
 * 		}
 * 
 * 		if(오른쪽 자식 노드강 없을 때) {
 * 			tree 배열에 -1 저장하기
 * 		}else {
 * 			tree 배열에 오른쪽 자식 노드 인덱스 저장하기
 * 		}
 * }
 * 
 * preOrder 실행하기 -> inOrder 실행하기 ->  postOrder 실행하기
 * 
 * preOrder {		// 중간 -> 왼쪽 -> 오른쪽
 * 		현재값이 -1이면 리턴(자식 노드가 없을 때)
 * 		1. 현재 노드 출력하기
 * 		2. 왼쪽 자식 노드 탐색하기
 * 		3. 오른쪽 자식 노드 탐색하기
 * }
 * 
 * inOrder {		// 왼쪽 -> 중간 -> 오른쪽
 * 		현재값이 -1이면 리턴(자식 노드가 없을 때)
 * 		1. 왼쪽 자식 노드 탐색하기
 * 		2. 현재 노드 출력하기
 * 		3. 오른쪽 자식 노드 탐색하기
 * }
 * 
 * postOrder {		// 왼쪽 -> 오른쪽 -> 중간
 * 		현재값이 -1이면 리턴(자식 노드가 없을 때)
 * 		1. 왼쪽 자식 노드 탐색하기
 * 		2. 오른쪽 자식 노드 탐색하기
 * 		3. 현재 노드 출력하기
 * }
 * 
 * **/
public class 트리_순회하기 {

	static int[][] tree;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
				
		//index: 알파벳의 인덱스(실제적 key)
		// x: 알파벳 전체 수		// y: 왼쪽, 오른쪽 노드 저장할거라서 2
		// ex) A의 왼쪽 오른쪽을 노드를 표현할 때는 tree[0][0] = 1(B);		tree[0][1] = 2(C);
		tree = new int[26][2];
		
		for(int i=0; i<n; i++) {
			String[] temp = sc.nextLine().split(" ");
			int node = temp[0].charAt(0)- 'A';
			char left = temp[1].charAt(0);
			char right = temp[2].charAt(0);
			
			if(left == '.') {
				tree[node][0] = -1;				// 공백은 -1로 표현
			} else {
				tree[node][0] = left - 'A';		// 알파벳 인덱스를 tree[][]에 저장 // ex)A: 0, B: 1, C: 2...
			}
			
			if(right == '.') {
				tree[node][1] = -1;
			} else {
				tree[node][1] = right - 'A';
			}
		}
		
		preOrder(0);
		System.out.println();
		inOrder(0);
		System.out.println();
		postOrder(0);
		System.out.println();
	}
	
	
	// 중간 -> 왼쪽 -> 오른쪽 
	public static void preOrder(int now) {
		if(now == -1) return;		// 탐색 종료
		
		System.out.print((char) (now + 'A'));					// 인덱스를 알파벳으로 변환해서 출력
		preOrder(tree[now][0]);		// 왼쪽으로 계속 내려감(재귀)		// 왼쪽 끝까지 내려갔다가 -1 만나면 종료
		preOrder(tree[now][1]);		// 오른쪽으로 계속 내려감(재귀)		// 오른쪽 끝까지 내려갔다가 -1 만나면 종료
	}
	
	// 왼쪽 -> 중간 -> 오른쪽 
	public static void inOrder(int now) {
		if(now == -1) return;		// 탐색 종료
		
		// (1)
		inOrder(tree[now][0]);		// 일단 왼쪽 끝까지 내려가기 	// -1나오면 로직 종료 
		
		// (2)
		System.out.print((char) (now + 'A'));	// 왼쪽 끝까지 내려간 상태	// 인덱스를 알파벳으로 변환해서 출력	
		
		// (3)
		// case1: 오른쪽 노드가 없을 때) -1 나오고 로직 종료 	
		// case2: 오른쪽 노드가 있을 때)  오른쪽 한칸 내려 갔다가, 거기서 또 왼쪽 끝까지 내려가서 -1만나고 알파벳으로 변환해서 출력
				  // 재귀적으로 실행되기 때문에 출력한 다음에 부모 노드의 로직이 다시 실행됨.
				  // 부모노드는 (1)까지 실행이 종료된 상태이기 때문에 출력되고 오른쪽으로 내려감 
		inOrder(tree[now][1]);		
	}
	
	// 왼쪽 -> 오른쪽 -> 중간 
	public static void postOrder(int now) {
		if(now == -1) return;		// 탐색 종료
		
		// inOrder를 응용해서 생각하면 쉽게 이해 됨.
		
		postOrder(tree[now][0]);		// 왼쪽으로 계속 내려감(재귀)		// 왼쪽 끝까지 내려갔다가 -1 만나면 종료
		postOrder(tree[now][1]);		// 오른쪽으로 계속 내려감(재귀)		// 오른쪽 끝까지 내려갔다가 -1 만나면 종료
		System.out.print((char) (now + 'A'));		// 인덱스를 알파벳으로 변환해서 출력ㄴ
	}
		

}
