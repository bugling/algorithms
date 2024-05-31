package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주몽의_명령 { 

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		/**
		 * [ ���� ]
		 * 
		 * N: �迭�� ������
		 * M: �� ���� ���Ͽ� ����� �ϴ� ��
		 * 
		 * N�� M, ������ �迭�� �Է¹ް� �迭�� ������Ʈ �� ���� ������ �� M�� ������ ���� �� ������ ���ϴ� �˰���.
		 * 
		 * [ �����ڵ� ]
		 * 
		 * 1) �迭 A ����
		 * 2) �� ����Ʈ i, j ���� => A[i]�� �ּҰ�, A[j]�� �ִ밪���� ���õ�
		 * 3) i�� j�� ������ �������� �ݺ��� ���� => i<j�� ��� ��� �ݺ��� ����ǵ��� �ڵ� �ۼ�
		 * 4) ���ǹ�
		 * 		- �� ���� ���� ��ǥ�պ��� ������ ���� ���� ����Ű�� �ε����� ���� : A[i] + A[j] < M	=> i++
		 * 		- �� ���� ���� ��ǥ�պ��� ũ�� ū ���� ����Ű�� �ε����� ���� : A[i] + A[j] > M => j--
		 * 		- �� ���� ���� ��ǥ�հ� ���ٸ� �� �ε��� ��� �̵� : A[i] + A[j] == M => i++ / j--
		 * **/
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N 	= Integer.parseInt(br.readLine());
		int M 	= Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		int count = 0;
		int i = 0;		//A[0] -> Min
		int j = N-1;	//A[N-1] -> Max
		
		while(i<j) {
			if(A[i] + A[j] < M) {
				i++;
			}else if(A[i] + A[j] > M) {
				j--;
			}else {	// A[i] + A[j] == M
				count++;
				
				System.out.println("A[i]:: " + A[i] + "  , A[j] ::" + A[j]);
				
				i++;
				j--;
			}
		}
		
		System.out.println(count);
	}

}
