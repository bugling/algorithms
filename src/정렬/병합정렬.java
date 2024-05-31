package 정렬;

import java.util.Scanner;

public class 병합정렬 {

	static int[] buff;
	
	static void __mergeSort(int[] a, int left, int right) {
		
		// 조건에 맞지 않는 경우 : a의 요소가 1개인 경우, left == right인 경우	-> 분할 완료
		if(left < right) {
			
			int i;			// 정렬되지 않은 배열을 처음부터 끝까지 스캔하는 인덱스
			int center = (left + right) / 2;
			int p = 0;		// 전체 배열의 앞 부분(left ~ center)의 크기
			int j = 0;		// 전체 배열의 앞 부분(left ~ center)의 커서 인덱스
			int k = left;	// 재정렬된 배열의 인덱스
			
			__mergeSort(a, left, center);		// 배열의 앞부분을 재정렬
			__mergeSort(a, center+1, right);	// 배열의 뒷부분을 재정렬
			
			
			// 배열의 앞부분만 담는 임시배열 buff 셋팅
			for(i = left; i<=center; i++)
				buff[p++] = a[i];
			
			// 앞부분 배열buff는 j / 뒷부분 배열a는 i 인덱스를 포커스로 삼음
			while(i <= right && j < p)
				a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
			
			// 앞 부분 배열의 인덱스가 그 크기보다 작다면	// 인덱스가 크기만큼 이동하지 않았다면
			while(j < p)
				a[k++] = buff[j++];
			
		}
	}
	
	static void mergeSort(int[] a, int n) {
		buff = new int[n];
		
		__mergeSort(a, 0, n-1);
		
		buff = null;
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("병합 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i=0; i<nx; i++) {
			System.out.print("x["+i+"] : ");
			x[i] = stdIn.nextInt();
		}
		
		mergeSort(x, nx);
		
		for(int i=0; i<nx; i++) {
			System.out.print("  x[" + i + "]= " + x[i]);
		}
		
	}

}
