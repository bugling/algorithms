package 정렬;

import java.util.Scanner;

public class 퀵정렬 {

	/**
	 * 
	 * [  설명 ]
	 * 기준값을 선정해 해당 값보다 작은 데이터와 큰 데이터로 분류하는 것을 반복
	 * pivot을 중심으로 계속 데이터를 2개의 집합으로 나누면서 정렬
	 * 기준값이 어떻게 선정되었는지가 시간 복잡도에 많은 영향을 미침
	 * 평균: O(nlogn) 최악: O(n제곱)
	 * 
	 * 1. 데이터를 분할하는 pivot을 설정한다.
	 * 2. pivot을 기준으로 a~e과정을 거쳐 데이터를 2개의 집합으로 분리한다.
	 *		a. start가 가리키는 데이터가 pivot이 가리키는 데이터보다 작으면 start를 오른쪽으로 1칸 이동
	 *		b. end가 가리키는 데이터가 pivot이 가리키는 데이터보다 크면 end를 왼쪽으로 1칸 이동
	 *		c. start가 가리키는 데이터가 pivot이 가리키는 데이터보다 크고,  
	 * 			end가 가리키는 데이터가 pivot이 가리키는 데이터보다 작으면 start, end가 가리키는 데이터를 swap하고
	 * 			start는 오른쪽, end는 왼쪽으로 1칸씩 이동한다.
	 * 		d. star와 end가 만날 때까지 a~c를 반복한다.
	 * 		e. start와 end가 만나면 만난 지점에서 가리키는 데이터와 pivot이 가리키는 데이터를 비교하여
	 * 			pivot이 가리키는 데이터가 크면 만난 지점의 오른쪽에, 작으면 만난 지점의 왼쪽에 pivot이 가리키는 데이터를 삽입한다.
	 * 3. 분리 집합에서 각각 pivot을 선정한다.
	 * 4. 분리 집합이 1개 이하가 될 때까지 1~3을 반복한다.
	 * 
	 * 
	 **/
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void quickSort(int[] a, int left, int right) {
		int pl = left;				// 왼쪽 커서
		int pr = right;				// 오른쪽 커서
		int x = a[(pl + pr) / 2];	// 피벗
		
//		System.out.println("x(피벗) = " + x);
//		System.out.printf("a[%d]~a[%d] : {", left, right);
//		for(int i=left; i<right; i++) {
//			
//			System.out.printf("%d , ", a[i]);
//		}
//		System.out.printf("%d}\n", a[right]);
		
		do {
			
			while(a[pl] < x) pl++;	// 왼쪽값이 피벗값보다 작을 때 오른쪽으로 이동		// 피벗값보다 크거나 같으면 멈춤
			while(a[pr] > x) pr--;	// 오른쪽값이 피벗값보다 클 때 왼쪽으로 이동		// 피벗값보다 작거나 같을 때 멈춤
			
			// 두 지점이 교차하기 전까지 swap실행
			if(pl <= pr) {
				swap(a, pl++, pr--);	// swap실행 후 pl++, pr--실행
			}
			
		} while(pl <= pr);	// 오른쪽 커서가 왼쪽 커서보다 크거나 같을 때에만 실행됨	
	
		if(left < pr) quickSort(a, left, pr);		// left >= pr인 경우는 그룹 내부 요소가 1개
		if(pl < right) quickSort(a, pl, right);		// pl <= right인 경우는 그룹 내부 요소가 1개
		
	}
	
	public static void main(String[] args) {
		
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("퀵 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i=0; i<nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		
		quickSort(x, 0, nx-1);		// 배열 x를 퀵정렬
		
		System.out.println("오름차순으로 정렬헀습니다.");
		for(int i=0; i<nx; i++) {
			System.out.print("x[" + i + "] = " + x[i] + "  ");
		}
		
	}
  		
}
