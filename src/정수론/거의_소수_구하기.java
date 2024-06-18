package 정수론;

import java.util.Scanner;

/**
 * 백준 1456
 * 
 * [ 문제 ]
 * 거의 소수: 소수의 N제곱(N>=2)인 수
 * A와 B가 주어질 때 A보다 크거나 같고, B보다 작거나 같은 '거의 소수'가 몇 개인지 출력하는 로직 
 * 
 * [ 입력 ]
 * 1 1000	// A B
 * 
 * [ 출력 ]
 * 25
 * 
 * [ 설명 ]
 * 1. 2 ~ 10,000,000 사이에 존재하는 모든 소수를 구합니다.
 * 2. 각각의 소수에 관해 소수를 N제곱한 값이 B보다 커질 때까지 반복문을 실행한다.
 *    이때 소수를 N제곱한 값이 A보다 크거나 같으면 거의 소수로 판단해 카운트한다.
 *    모든 소수에 관해서는 반복문을 실행한 후 카운트한 값을 출력한다.
 * 	  * N제곱을 구하는 도중 값의 범위가 long형을 초과하는 경우가 발생
 *      따라서 계산 오류를 방지하려면 N^k와 B값이 아니라 N과 B/N^(k-1)을 비교하는 형식으로 식을 적절하게 정리해야 함.
 *      
 * [ 슈도코딩 ]
 * M: 시작 수
 * Max: 종료 수
 * A: 소수 배열
 * for(2~10,000,000)		// 10^14의 제곱근인 10^7까지 반복
 * {
 * 		A배열 초기화하기
 * }
 * 
 * for(2~10,000,000)
 * {
 * 		A배열에서 소수인 값 일 때
 * 		temp = 현재 소수
 * 		
 * 		// 현재 소수의 제곱근이 Max보다 작을 떄를 기준으로 하지만
 * 		// 곱셈이 long의 범위를 넘어갈 수 있어 이항 정리로 처리하기
 * 		while(현재 소수 <= Max/temp)						// A[i] * temp <= Max를 이항정리한 것	(제곱값이 Max를 넘지 않을 때까지 반복한다는 뜻)
 * 		{
 * 			if(현재 소수 >= Min/temp) 정답값 증가
 * 			temp = temp * 현재 소수
 * 		}
 * }
 * 
 * 정답 출력하기
 *      
 * **/
public class 거의_소수_구하기 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		long Min = sc.nextLong();
		long Max = sc.nextLong();
		long[] A = new long[10000001];
		
		for(int i=2; i<A.length; i++) {
			A[i] = i;
		}
		
		for(int i=2; i<Math.sqrt(A.length); i++) {		// 제곱근까지만 수행
			if(A[i] == 0) {
				continue;
			}
			for(int j=2*i; j<A.length; j=j+i) {
				A[j] = 0;
			}
		}
		
		int count = 0;
		for(int i=2; i<10000001; i++) {
			if(A[i] != 0) {
				
				long temp = A[i];			// temp = A[i] ^ 1
				
				while((double)A[i] <= (double)Max / (double)temp) {		// A[i] * temp <= Max를 이항정리한 것	(제곱값이 Max를 넘지 않을 때까지 반복한다는 뜻)
			//		if((double)A[i] >= (double)Min/(double)temp) {
						count++;
			//		}
					
					temp = temp * A[i];		// A[i]의 제곱값을 temp에 저장한다.
				}
			}
		}
		
		System.out.println(count);
	}

}
