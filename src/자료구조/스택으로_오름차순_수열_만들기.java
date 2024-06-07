package 자료구조;

import java.util.Scanner;
import java.util.Stack;


/**
 * [ 슈도 코딩 ]
 * 
 * N:수열 개수
 * A[]: 수열 배열
 * 
 * 수열 배열 채우기
 * 
 * for(N만큼 반복하기)
 * {
 * 		if(현재 수열 값 >= 오름차순 자연수){
 * 			while(값이 같아질 때까지)
 * 			{
 * 				push();
 * 				(+)저장
 * 			}
 * 			pop();
 * 			(-)저장
 * 		}
 * }
 * else(현재 수열 값 < 오름차순 자연수) {
 * 		pop();
 * 		if(스택 pop 결괏값 > 수열의 수) NO출력
 * 		else (-) 저장
 * }
 * 
 * [ 입력 값 ]
8
4
3
6
8
7
5
2
1
	[ 결과 ]
+
+
+
+
-
-
+
+
-
+
+
-
-
-
-
-
 * 
 * **/
public class 스택으로_오름차순_수열_만들기 {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		
		Stack<Integer> stack = new Stack<>();
		
		StringBuffer bf = new StringBuffer();
		int num = 1;		// 오름차순 수(스택에 쌓이는 값을 담는 변수)
		boolean result = true;
		
		for(int i=0; i<A.length; i++) {
			int su = A[i];
			
			if(su >= num) {
				while(su >= num) {
					stack.push(num++);
					bf.append("+\n");
				}
			
			stack.pop();
			bf.append("-\n");
			
			}else {
				
				int n = stack.pop();
				
				// 스택에서 빼낸 수가 입력받은 수 보다 작을 때 NO출력 후 로직 종료
				// 	이전에 반복문이 실행되며 이미 빼내었기 때문
				/**
				 * 
				 * 1, 2, 5, 3, 4 순서대로 stack에 쌓일 때
				 * 1: push(1) -> pop()
				 * 2: push(2) -> pop()
				 * 5: push(3) -> pop() -> push(4) -> push(5) -> pop()
				 * 3: pop() -> pop()
				 * 
				 * 4: NO		**이전에 3까지 pop되었기 때문에 다시 쌓을 수 없음
				 * 
				 **/
				if(n > su) {
					result = false;
					break;
				}
				else {
					
					bf.append("-\n");
				}
			}
			
		}
		if(result) System.out.println( bf.toString() );
	}

}
