package chapter04;

/**
 * modifier(제어자)
 * <p>
 * access modifier		public 	protected	생략(default)	private
 * usage  modifier		abstract	static	final
 * <p>
 * access modifier	: 클래스, 메서드, 속성에 대한 접근을 제한한다.
 * public 		: 접근 제한이 전혀 없다.
 * protected	: 상속 관계에서는 제한이 없다. 상속 관계가 아니면 같은 package가 아닌 경우 접근 불가
 * 생략(default): 같은 package가 아닌 경우 접근 불가
 * private		: 선언한 클래스 내에서만 접근 가능
 * <p>
 * usage  modifier	: 클래스, 메서드, 속성의 기능을 제한한다.
 */
public class MyDate {
	private int year, month, day;
	String name;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year < 2024)
			System.out.println("2024년 이후로 설정해주세요");
		else this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}


















