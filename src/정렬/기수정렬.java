package 정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 기수정렬 {		// RadixSort

	/**
	 * 
	 * [슈도코딩]
	 * N: 정렬할 수 개수
	 * A: 정렬할 배열 선언하기
	 * 
	 * for(N의 개수만큼 반복하기)
	 * {
	 * 		A배열 저장하기
	 * }
	 * 기수 정렬 함수 수행하기
	 * 정렬된 A 배열 출력하기
	 * 
	 * // 기수 정렬 함수 구현하기
	 * // 변수 선언부
	 * 
	 * bucket(현재 자릿수들의 분포를 합 배열의 형태로 알려주는 배열)
	 * ex: bucket[4] -> 현재 기준 자릿값이 0~4까지 몇 개의 데이터가 있는지 저장하기
	 *  
	 * output(임시 정렬을 위한 배열)
	 * jarisu(현재 자릿수를 표현하는 수)
	 * 
	 * while(최대 자릿수만큼 반복하기)
	 * {
	 * 		현재 기준 자릿수를 기준으로 A배열 데이터를 buckt에 count
	 * 		합 배열 공식으로 bucket을 합 배열 형태로 변경하기
	 * 		for(i가 N-1에서 0까지 감소하면서 반복문 실행)
	 * 		{
	 * 			bucket값을 이용해 현재 기준 자릿수로 데이터를 정렬하기
	 * 			output 배열에 저장하기
	 * 		}
	 * }
	 * 
	 * for(N의 개수만큼 반복하기)
	 * {
	 * 		다음 자릿수 이동을 위한 A배열에 output 데이터 저장하기
	 * }
	 * 
	 * jarisu = jarisu * 10;
	 * 
	 * **/
	public static int[] A;
	public static long result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		Radix_Sort(A, 5);
		
		for(int i=0; i<N; i++) {
			bw.write(A[i] + " ");
		}
		bw.flush();
		bw.close();
		
	}
	
	public static void Radix_Sort(int[] A, int max_size) {		// max_size: 최대 자리수의 인덱스 **5: 10000자리라는 뜻
		int[] output = new int[A.length];	// 배열의 길이만큼 새로운 배열 output선언
		int jarisu = 1;		// 자리수 1, 10, 100, 1000
		int count = 0;		// 자리수 인덱스
		while(count != max_size)
		{
			int[] bucket = new int[10];			// A[i]값을 각 자리 수를 뽑아 0 ~ 9로 나누어 개수를 세는 배열
			
			// 전체 배열 A를 돌며 
			for(int i=0; i<A.length; i++) {	
				// ex) 56 -> jarisu=1 :: (56 / 1) % 10 = 6		// jarisu=10 :: (56 / 10) % 10 = 5
				//			bucket[6]++							// 				bucket[5]++
				bucket[(A[i] / jarisu) % 10]++;					// 숫자를 각 자리수 별로 잘라내서 해당되는 bucket의 값에 +1을 한다
			}
			
			for(int i=1; i<10; i++) {			// 합 배열을 이용해 index 계산하기
				
				// bucket[1]=bucket[1]+bucket[0] , bucket[2]=bucket[2]+bucket[1]	...		bucket[9]=bucket[9]+bucket[8]
				bucket[i] += bucket[i-1];		// bucket을 돌며 누적합을 저장한다.	bucket[10]에는 bucket[0~9]까지의 합이 담겨있다.
			}
			
			for(int i=A.length -1; i>=0; i--) {	// A의 마지막 인덱스에서 0까지 돈다.
				
				// A[i] / jarisu % 10)] - 1	 ---> 	{ ( 56 / 10 ) % 10 } - 1 = 4
				// output[4] = 56;
				// bucket[6]--;
				output[bucket[(A[i] / jarisu % 10)] - 1] = A[i];	// A의 값을 
				bucket[(A[i] / jarisu) % 10]--;
			}
			
			for(int i=0; i<A.length; i++) {
				A[i] = output[i];
			}
			jarisu = jarisu * 10;
			count++;
		}
	}

}
