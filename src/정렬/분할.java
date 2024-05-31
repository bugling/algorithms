package 정렬;

import java.util.Scanner;

public class 분할 {
	
	/**
	 * 배열 요소 a[idx1]과 a[idx2]의 값을 바꿉니다.
	 * **/
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	/**
	 * 배열을 나눕니다.
	 * **/
	static void partition(int[] a, int n) {
		
		int pl = 0;			// 왼쪽 커서
		int pr = n - 1;		// 오른쪽 커서		// indxt = size - 1
		int x = a[n / 2];	// 피벗(가운데 위치의 값)
		
		do {
			
			while(a[pl] < x) pl++;	// 왼쪽 끝에서 피벗보다 작은 값을 만나면 오른쪽으로 이동한다.	// 피벗보다 크거나 같은 값을 만날때까지 이동
			while(a[pr] > x) pr--;	// 오른쪽 끝에서 피벗보다 큰 값을 만나면 왼쪽으로 이동한다.		// 피벗보다 작거나 같은 값을 만날때까지 이동
			
			// pl과 pr이 교차되기 전까지 실행됨.
			if(pl <= pr) {
				swap(a, pl++, pr--);		// swap실행 후 pl++, pr-- 실행됨!
			}
			
		} while(pl <= pr);		// 오른쪽 커서가 왼쪽커서보다 같거나 클때		// 마지막 while문 실행 시 pl과 pr은 같은 지점에 있고, 마지막으로 do에 진입해서 pl과 pr이 교차하고 종료된다.
	
		System.out.println("피벗 값은 " + x + " 입니다.");
		
		System.out.println("피벗 이하의 그룹");
		for(int i=0; i<=pl-1; i++) {		// pl이 pivot보다 1칸 오른쪽으로 간 상태라서 pl-1이하이면 pivot을 포함한 그 이하의 값들을 출력한다.
			System.out.print(a[i] + " ");
		}
		
		System.out.println();
		
		if(pl >= pr+1) {
			System.out.println("피벗과 일치하는 그룹");
			for(int i=pr+1; i<=pl-1; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}
		
		System.out.println("피벗 이상의 그룹");
		for(int i=pr+1; i<n; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		
		
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("배열을 나눕니다.");
		System.out.print("요솟수 : ");
		
		int nx = stdIn.nextInt();	// 배열의 크기
		int[] x = new int[nx];
		
		for(int i=0; i<nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		
		partition(x, nx);			// 배열 x를 나눕니다.
	}

}
