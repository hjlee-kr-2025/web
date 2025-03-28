package javabasic.ch02;

public class DataType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		short num1 = 255;
		byte num2 = (byte) num1;
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);
		num1 = 1;
		num2 = 1;
		System.out.println("num1 = " + (num1++));
		System.out.println("num2 = " + (++num2));
	}

}
