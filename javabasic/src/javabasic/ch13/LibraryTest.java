package javabasic.ch13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Book {
	private String name;
	private int price;
	
	public Book(String name, int price) {
		this.name = name;
		this.price = price;
	}
	// 스트림 구성전에 getter를 만듭니다.
	public int getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	
}

public class LibraryTest {
	public static void main(String[] args) {
		List<Book> bookList = new ArrayList<Book>();
		
		bookList.add(new Book("자바", 25000));
		bookList.add(new Book("파이썬", 15000));
		bookList.add(new Book("안드로이드", 30000));
		bookList.add(new Book("HTML", 10000));
		bookList.add(new Book("CSS", 5000));
		bookList.add(new Book("Javascript", 17000));
		bookList.add(new Book("MySQL", 20000));
		
		// 모든 책 가격의 합
		int sum = bookList.stream().mapToInt(b->b.getPrice()).sum();
		System.out.println("모든 책 가격의 합은 " + sum + "원 입니다.");
		
		// 가격이 20000원 이상인 책의 이름을 정렬하여 출력
		System.out.println("20000 만원 이상인 책 이름");
		bookList.stream().filter(b->b.getPrice() >= 20000)
			.map(b->b.getName()).sorted(Comparator.reverseOrder())
			.forEach(n->System.out.println(n));
		
		// sorted() : 오름차순 정렬
		// sorted(Comparator.reverseOrder()) 사용시 내림차순정렬
	}
}







