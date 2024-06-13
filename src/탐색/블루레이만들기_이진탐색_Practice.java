package 탐색;

import java.util.Scanner;

/**
 * 
 * 백준 2343번
 * 
 * [ 문제 ]
 * 강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다.
 * 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다.
 * 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다.
 * 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.
 * 강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에, 블루레이의 개수를 가급적 줄이려고 한다.
 * 오랜 고민 끝에 강토는 M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다.
 * 이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다.
 * 단, M개의 블루레이는 모두 같은 크기이어야 한다.
 * 강토의 각 강의의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.
 * 
 * [ 분석 ]
 * 블루레이의 크기가 모두 같고 녹화 순서가 바뀌지 않아야 함		-> 		이진트리 탐색
 * 모두 저정할 수 있으면 블루레이의 크기를 줄이고 저장할 수 업다면 블루레이의 크기를 늘리는 방식
 * 
 * [ 슈도 코딩 ]
 * N: 레슨 개수
 * M: 블루레이 개수
 * start: 이진 탐색 시작 인덱스
 * end: 이진 탐색 종료 인덱스
 * 
 * for(N의 개수만큼 반복하기)
 * {
 * 		A배열 저장하기
 * 		시작 인덱스 저장(A 배열 중 최댓값)
 * 		종료 인덱스 저장(A 배열의 총합)
 * }
 * 
 * while(시작 인덱스 <= 종료 인덱스) {
 * 		middle: 중간 인덱스
 * 		sum: 레슨 합
 * 		count: 현재 사용한 블루레이 개수
 * 		for(N의 개수만큼 반복하기)
 * 		{
 * 			만약 ( sum + 현재 레슨 시간 ) > 중간 인덱스이면
 * 				count값을 올리고 sum을 0으로 리셋하기
 * 
 * 			// 현재 블루레이에 저장할 수 없어 새로운 블루레이로 교체한다는 의미
 * 			sum에 현재 레슨 시간값 더하기
 * 		}
 * 
 * 		sum이 0이 아니면 마지막 블루레이가 필요하므로 count값 올리기
 * 		if(count > M: 중간 인덱스값으로 모든 레슨 저장 불가능) 시작 인덱스 = 중앙 인덱스 + 1
 * 					else(중간 인덱스값으로 모든 레슨 저장 가능) 중료 인덱스 = 중앙 인덱스 - 1
 * }
 * 
 * 시작 인덱스 출력하기
 * 
 * **/
public class 블루레이만들기_이진탐색_Practice {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] A = new int[N];
		
		int start 	= 0;
		int end 	= 0;
		
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
			if(start < A[i]) start = A[i];		// 레슨 최댓값을 시작 인덱스로 저장하기
			end = end + A[i];					// 모든 레슨의 총합을 종료 인덱스로 저장하기
		}
		
		while(start <= end) {
			int middle = (start + end) / 2;
			int sum = 0;
			int count = 0;
			
			// 레슨의 개수만큼 반복
			for(int i=0; i<N; i++) {			// middle값으로 모든 레슨을 저장할 수 있는지 확인
				if(sum + A[i] > middle) {		// 누적합이 중위값보다 높으면
					count++;					// 카운트를 올리고
					sum = 0;					// 누적합을 초기화한다.
				}
				sum = sum + A[i];				// 반복문이 끝날 때마다 레슨의 누적합을 계산한다.
			}
			
			if(sum != 0)						// 반복문이 끝난 후 누적합이 초기화되지 않고 쌓여있다면
				count++;						// 카운트를 올린다.
			
			if(count > M)						// 카운트가 입력받은 블루레이 수 보다 크면
				start = middle + 1;				// 시작지점을 중위값 바로 뒤로 옮긴다.
		}
		
		System.out.println(start);
		
		
	}

}
