package 정렬;

import java.util.Scanner;
import java.util.Stack;

public class 비재귀적_퀵_정렬 {

	static void quickSort(int[] a, int left, int right) {
		
		Stack lstack = new Stack();
		
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("퀵 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i=0; i<nx; i++) {
			System.out.print("x[" + i + "]");
			x[i] = stdIn.nextInt();
		}
		
		quickSort(x, 0, nx-1);
		
		
		
	}

}
