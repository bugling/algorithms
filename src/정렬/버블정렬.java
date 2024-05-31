package 정렬;

import java.util.Scanner;

public class 버블정렬 {

	
	/**
	 * 
	 * [ 슈도코딩 ]
	 * N: 입력받을 배열의 길이
	 * A: 정렬할 배열 선언
	 * 
	 * for(i : 0 ~ N-1)
	 * {
	 * 		for(j : 0 ~ N-1-i)
	 * 		{
	 * 			현재 A 배열의 값보다 1칸 오른쪽 배열의 값이 더 작으면 두 수 바꾸기
	 * 		}
	 * }
	 *
	 * A 배열 출력
	 *
	 **/
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int A[] = new int[N];
		
		// 자료 받기
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		
		// 버블 정렬을 구현하는 영역
		for(int i=0; i<N-1; i++) {
			for(int j=0; j<N-1-i; j++) {	
			// j<N-1-i : 바깥 루프가 한 번 돌 때 마다 배열의 마지막 자리에 수가 정렬되기 때문에
			// 			 내부 for문의 반복 횟수를 1씩 감소시킴
				
				if(A[j] > A[j+1]) {
					int temp = A[j];
					A[j] = A[j+1];
					A[j+1] = temp;
				}
				
				
			}
		}
		
		
		for(int i=0; i<N; i++) {
			System.out.print(A[i] + " ");
		}
		
	}

}
