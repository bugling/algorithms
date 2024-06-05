package 탐색;

import java.util.Scanner;

/**
 * 백준 온라인 저지 2023번
 * 
 * [ 문제 ]
 * 7331, 733, 73, 7 처럼 앞 자리부터 1의 자리씩 올렸을 때도 소수인 수를 '신기한 소수'라고 부르로 했다. 		**다른 예) 2339
 * N의 자리 숫자 중 어떤 수들이 신기한 소수인지 찾아보자.
 * 
 * [ 입력 ]
 * 1번째 줄에 1<=N<=8 이 주어짐
 * 
 * [출력]
 * N의 자리 숫자 중 신기한 소수를 오름차순 정렬해 1줄에 1개씩 출력한다.
 * 
 * *소수: 약수가 1과 자기 자신인 수
 * 
 * ------------------------------------------------------------------------------
 * 
 * 
 * [ 슈도코딩 ]
 * 
 * N: 자리수
 * DFS실행
 * DFS(2, 1)	// 1자리 소수: 2, 3, 5, 7
 * DFS(3, 1)
 * DFS(5, 1)
 * DFS(7, 1)
 *
 * // DFS구현하기
 * DFS(숫자, 자리수) {
 * 
 * 		if(자리수 == N) {
 * 			if(소수) 수 출력하기
 * 			탐색 종료
 * 		}
 * 
 * 		for(1 ~ 9 반복하기) {
 * 			if(뒤에 붙는 수가 홀수이면서 소수일 때) {
 * 				DFS(현재 수 * 10 + 1, 자리수 + 1)
 * 			}
 * 		}
 * }
 * 
 * // 소수 구하기 함수
 * for('2 ~ 현재 수 / 2' 반복하기) {
 * 		if(나머지가 0이면) return 소수가 아님
 * } 
 * return 소수임;
 * 
 * **/
public class 신기한소수찾기_DFS_Practice {

	static int N;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		
		DFS(2, 1);		// 일의 자리 소수가 2, 3, 5, 7이므로 4개 수에서만 시작
		
		for(int number=3; number<10; number++) {
			DFS(number, 1);
		}
	}
	
	
	static void DFS(int number, int jarisu) {
		
		// 사용자가 입력한 자리수에 도달하면 소수인지 판별하고 출력함.	->  재귀함수 종료
		if(jarisu == N) {
			if(isPrime(number)) {
				System.out.println(number);
			}
			return;
		}
		
		// i -> 1~9까지 반복
		for(int i=1; i<10; i++) {
			if(i % 2 == 0) {	// 짝수이면
				continue;		// 더 이상 탐색하지 않음 
			}
			
			// i = 1, 3, 5, 7, 9
			// number*10+1 :: number=2 -> 2*10+1, 2*10+3 ,,, 2*10+9
			if(isPrime(number*10+i)) {		// 소수라면 재귀 함수로 자릿수를 늘림
				DFS(number*10+i, jarisu+1);
			}
		}
	}
	
	//----------------------------------
	// 소수인지 판별하는 메서드
	//----------------------------------
	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) {		// 인자로 들어온 num을 1씩 증가시키며 나눈다.
				return false;		// 나누어 떨어지는 수가 있으면 false를 반환
			}
		}
		
		return true;				// 나누어 떵러지는 수가 없으면 true를 반환
	}

}
