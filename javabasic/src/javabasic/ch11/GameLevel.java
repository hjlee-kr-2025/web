package javabasic.ch11;

public enum GameLevel {

	BEGINNER_LEVEL(1, "초보자"),
	ADVANCED_LEVEL(2, "숙련자"),
	SUPER_LEVEL(3, "고수");
	
	// 상수가 가질 수 있는 속성을 변수로 지정
	private int level;
	private String hint;
	
	// 상수가 가지는 값을 생성자로 초기화
	GameLevel(int level, String hint) {
		this.level = level;
		this.hint = hint;
	}
	
	// 상수가 가진 속성값에 대한 getter 
	public int getLevel() {
		return level;
	}
	
	public String getHint() {
		return hint;
	}
	
	// 상수이기 때문에 setter는 존재하지 않습니다.
}
