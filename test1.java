package lhh;

import java.util.Scanner;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           test1();
	}
	
	/*
	 * ����ڷ� ���� �԰��� ������ �Է����� �� ũ�⸦ �Է��޴¤�
	 * �� ũ���� �迭������ �����Ѵ�
	 * 
	 * �迭�� ũ�⸸ŭ ������ �ݺ��Ѵ�
	 * ����ڷκ��� ������ �Է¹޾� �迭�� ������� �����Ѵ�
	 * 
	 * �迭�� ù��° ���Һ��� ������ ���ұ��� ����Ѵ�
	 * �� �������� ����� ����Ѵ�
	 * �Էµ� ���� �� �ִ� ������ ����Ѵ�
	 */
	    public static void test1() {
		Scanner input = new Scanner(System.in);
		System.out.println("�� ���� ������ �Է��Ͻðڽ��ϴٱ�?");
		
		int count = input.nextInt();
		
		int[] scores = new int[count];
		
		int sum = 0;
		
		for(int idx = 0; idx < count; idx++) {
			System.out.println(idx+1+"��° ������ �Է��ϼ���");
			int score = input.nextInt();
			
			scores[idx] = score;
			sum += score; //sum = sum + score
		}
		
		int max = scores[0];
		
		System.out.println("�Էµ� ������ ������ �����ϴ�...");
		for (int idx = 0; idx < count; idx++) {
			System.out.println(scores[idx]);
			if(scores[idx] > max) {
				max = scores[idx];
			}
		}
		System.out.println("��� : " + (double)sum/count);
		System.out.println("�ִ밪�� : " + max);
	}

}
