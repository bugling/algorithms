package 탐색;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 백준: 1920번
 * 
 * [ 슈도 코딩 ]
 * N: 정렬할 수 개수
 * M: 탐샋할 숫자의 개수
 * A: 정렬할 배열 선언하기
 * 
 * for(N의 개수만큼 반복)
 * {
 * 		A 배열 저장하기
 * }
 * A배열 정렬하기
 * 
 * for(M의 개수만큼 반복하기)
 * {
 * 		target(찾아야 하는 수)
 * 
 *		// 이진탐색 시작
 *		start(시작 인덱스),
 *		end(종료 인덱스)
 *		while(시작 인덱스 <= 종료 인덱스)
 *		{
 *			midi(중간 인덱스)
 *			if(중앙값 >= target) {
 *				종료 인덱스 = 중간 인덱스 - 1
 *			}
 *			else if(중앙값 < target) {
 *				시작 인덱스 = 중간 인덱스 + 1
 *			}
 *			else {
 *				찾았으므로 반복문 종료하기
 *			}
 *		}
 *
 *		if(찾았음) 1 출력
 *		else 0 출력
 * }
 * 
 * **/
public class 이진탐색 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();	// 탐색할 배열 입력받기			// 4 1 5 2 3
		}
		
		Arrays.sort(A);				// 입력받은 배열 정렬하기		// 1 2 3 4 5
		
		int M = sc.nextInt();		// 탐색할 숫자의 개수
		
		// 탐색할 숫자의 개수만큼 반복
		for(int i=0; i<M; i++) {
			
			boolean find = false;
			int target = sc.nextInt();	// 탐색할 수			// 1 3 7 9 5
			
			// 이진탐색 시작
			int start = 0;				// 시작 인덱스
			int end = A.length - 1;		// 끝 인덱스
			
			while(start <= end) {		// 시작인덱스가 끝 인덱스와 같아질 떄까지 반복(두 개의 인덱스가 만날 때까지)
				int midi = (start + end) / 2;	// 중앙 인덱스
				int midV = A[midi];				// 중위값
				
				// 중위값이 타겟보다 크다면
				if(midV > target) {
					end = midi - 1;		// 끝 인덱스를 기존 중앙 인덱스 바로 앞으로 옮긴다.
					
				} 
				// 중위값이 타겟보다 작다면
				else if(midV < target) {
					start = midi + 1;	// 시작 인덱스를 중앙 인덱스 바로 뒤로 옮긴다.
				}
				// 중위값과 타겟이 같다면
				else {
					
					find = true;		// 찾았다!
					break;				// while문 종료
				}
			}
			
			if(find) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
		}
		
		
		
	}

}
