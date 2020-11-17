package java_test_file;
import java.util.Scanner;
public class m11d17 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //main();
		//IfTest2();
		//IfTest3();
		//IfTest4();
		//IfTest5();
		//ForTest1();
		ForTest2();
 }
	public static void main() {
		Scanner input = new Scanner(System.in);
		
		int A = input.nextInt();
		String B = input.next();
		System.out.println(A * (B.charAt(2) - '0'));
		System.out.println(A * (B.charAt(1) - '0'));
		System.out.println(A * (B.charAt(0) - '0'));
		System.out.println(A * Integer.parseInt(B));
	}
	public static void IfTest1() {
		Scanner input = new Scanner(System.in);
		int a, b;
		a = input.nextInt();
		b = input.nextInt();
		if(a > b) {
			System.out.println(">");
		}else if(a < b) {
			System.out.println("<");
		}else if(a == b){
		    System.out.println("==");
		}
	}
	public static void IfTest2() {
		Scanner input = new Scanner(System.in);
		int jumsu, val;
		jumsu = input.nextInt();
		val = jumsu/10;
		if(val >=9) {
			System.out.println("A");
		}else if(val >= 8) {
			System.out.println("B");
		}else if(val >= 7) {
			System.out.println("C");
		}else if(val >= 6) {
			System.out.println("D");
		}else {
			System.out.println("F");
		}
	}
	public static void IfTest3() {
		Scanner input = new Scanner(System.in);
		int year;
		year = input.nextInt();
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			System.out.println("1");
		}else {
			System.out.println("0");
		}
	}
	public static void IfTest4() {
		Scanner input = new Scanner(System.in);
		int x, y;
		System.out.println("x좌표와 y좌표를 입력하시오.");
		x = input.nextInt();
		y = input.nextInt();
		if(x >= 0 && y >= 0) {
			System.out.println("1");
		}else if (x <= 0 && y >= 0) {
			System.out.println("2");
		}else if (x <= 0 && y <= 0) {
			System.out.println("3");
		}else {
			System.out.println("4");
		}
	}
	public static void IfTest5() {
		Scanner input = new Scanner(System.in);
		int h, m;
		h = input.nextInt();
		m = input.nextInt();
		if(m < 45) {
			h--;
			m = 60 - (45 - m);
			if(h < 0) {
				h = 23;
			}
			System.out.println(h + " " + m);
		}
		else {
			System.out.println(h + " " + (m - 45));
		}
	}
	public static void ForTest1() {
	  Scanner scan = new Scanner(System.in);
	  int num = scan.nextInt();
	  for(int i = 1; i < 10; i++) {
		  System.out.println(num + " * " + i + " = " +num*i);
	  }
	}
	public static void ForTest2() {
		Scanner input = new Scanner(System.in);
		int num, sum;
		sum = 0;
		num = input.nextInt();
		for(int i = 0; i <= num; i++) {
			sum = sum + i;	
		}input.close();
		System.out.println(sum);
	}
}
