package javabasic.ch11;

public class EnumTest {

	public static void main(String[] args) {
		GameLevel gamelevel1 = GameLevel.valueOf("BEGINNER_LEVEL"); 
		GameLevel gamelevel2 = GameLevel.valueOf("ADVANCED_LEVEL"); 
		GameLevel gamelevel3 = GameLevel.valueOf("SUPER_LEVEL");
		
		System.out.println(gamelevel1.getLevel());
		System.out.println(gamelevel2.getHint());
	}
}
