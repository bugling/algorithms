package 자료구조;

import java.util.Scanner;

/**
 * [ 설명 ]
 * 15=7+8, 10=1+2+3+4 처럼 어떤 수(N)가 연속된 수의 합으로 이루어지는 경우의 개수를 구하는 로직  
 * start_index: 1 	/ end_index: 1로 투 포인트를 두고 투 포인트를 이동해 가며 합을 구한다.
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * [ 투 포인터 이동 원칙 ]
 * sum > N: 	sum = sum - start_index; 	start_index++;
 * sum < N: 	end_index++; 				sum = sum + end_index;
 * sum == N: 	end_index++;				sum = sum + end_index; 		count++;
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * [ 슈도 코딩 ]
 * N 변수 저장		// 연속된 수의 합이 되는 수
 * 
 * 사용 변수 초기화(count = 1, start_index = 1, end_index = 1, sum = 1)
 * 
 * while(end_index != N)
 * {
 * 		if(sum == N) count증가, end_index증가, sum값 변경
 * 		else if(sum > N) sum값 변경, start_index증가
 * 		else if(sum < N) end_index 증가, sum값 변경
 * }
 * 
 * count 출력
 * 
 * **/
public class 연속된_자연수의_합_구하기 {

	public static void main(String[] args) {
		 
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int count = 1;
		int start_index = 1;
		int end_index = 1;
		int sum = 1;
		
		while(end_index != N) {
			if(sum == N) {
				count++;
				end_index++;
				sum = sum + end_index;
			}else if(sum > N) {
				sum = sum - start_index;
				start_index++;
			}else {		// sum < N
				
				end_index++;
				sum = sum + end_index;
			}
		}
		
		System.out.println(count);
	}

}
