package javabasic.ch11;

// gyshop에 있는 BoardVO 를 record로 만든다고 하면
public record BoardVO(Long no, String title, String content,
		String writer, String writeDate, Long hit,
		String pw) {

}

// 생성자 역할을 같이하는 클래스 ===> Record
// record 로 구성된 클래스는 멤버변수를 {} 안에 정의할 수 없지만
// 메서드는 자유롭게 구성할 수 있습니다. 