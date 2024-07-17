package 정렬;

import java.util.Scanner;

public class 선택정렬 {

	/**
	 * [ 설명 ]
	 * 
	 * 1) 남은 정렬 부분에서 최솟값(또는 최댓값)을 찾는다.
	 * 2) 남은 정렬 부분에서 가장 앞에 있는 데이터와 선택된 데이터를 swap한다.
	 * 3) 가장 앞에 있는 데이터의 위치를 변경해(index++) 남은 정렬의 부분의 범위를 축소한다.
	 * 4) 전체 데이터 크기만큼 index가 커질 때까지, 즉 남은 정렬 부분이 없을 때까지 반복한다.
	 * 
	 * ( 입력 예제 )
	 * 2143
 	 *
 	 * ( 출력 예제)
 	 * 4321 
 	 *
	 * [ 슈도코딩 ]
	 * 
	 * str(정렬할 수)
	 * A(자릿수별로 구분해 저장한 배열)
	 * 
	 * for(str의 길이만큼 반복하기) {
	 * 		A 배열 저장 -> str.substring 사용하기
	 * }
	 * 
	 * 
	 * for(i: 0~str의 길이만큼 반복하기) {		
	 * 		for(j: i+1 ~ str의 길이만큼 반복하기) {
	 * 			현재 범위에서 Max값 찾기
	 * 		}
	 * 		현재 i의 값과 Max값이 더 크면 swap 수행하기
	 * }	
	 * 
	 * A 배열 출력하기
	 * 
	 **/
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		int[] A = new int[str.length()];
		
		for(int i=0; i<str.length(); i++) {
			A[i] = Integer.parseInt(str.substring(i, i+1));
		}
		
		for(int i=0; i<str.length(); i++) {
			
			// i번째 요소를 최대값으로 지정
			int Max = i;
			
			// i번째 이후 요소 중에서 최대값 찾기	// 해당 요소의 인덱스를 Max로 지정
			for(int j=i+1; j<str.length(); j++) {
				if(A[j] > A[Max]) {
					Max = j;
				}
			}
			
		//	if(A[i] < A[Max]) {
				// i번째 요소와 i번째 이후의 최대값을 스왑
				int temp = A[i];
				A[i] = A[Max];
				A[Max] = temp;
		//	}
			
		}
		
		
		// 정렬된 배열 출력
		for(int i=0; i<str.length(); i++) {
			System.out.print(A[i] + "  ");
		}
		
	}
}
