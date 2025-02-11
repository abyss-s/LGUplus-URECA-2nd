package chapter05;

/**
 * 상속
 * - 부모(super class)로 부터 자산(속성, 메서드..)를 물려 받기
 * => 코드 재사용
 * - 단일 상속만 가능
 * - 생성자는 상속 대상에서 제외
 * <p>
 * 형식
 * [modifiers] class ClassName [extends 부모클래스이름]{
 * <p>
 * }
 */
public class MainCustomer extends Customer {
	/*
	 * 생성자는 상속 되지 않지만 호출을 통해 코드를 재사용할 수있다.
	 * super(~)
	 * 주의점 : 생성자 호출은 생성자의 첫번째 명령에서만 호출할 수 있다.
	 */
	private String hobby;

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public MainCustomer(String name, int age, String address, String hobby) {
		// this.name = name; // private은 상속 관계라도 접근 불가
		setName(name); // 선언하지 않았지만 상속 관계이기 때문에 호출 가능
		setAddress(address);
		setAge(age);
		this.hobby = hobby;
	}


	/**
	 * Method Override(메서드 재정의)
	 * 상속 받은 메서드와 동일한 이름의 메서드를 선언.
	 * - 상속 받은 메서드와  전체적인 기능을 동일하지만 상세 구현이 조금 다를 경우
	 * 기존의 상속 받은 메서드로 사용할 수 없으므로 새로운 메서드를 추가로 선언해야 한다.
	 * 이때 상속 받은 메서드와 이름, 인자, 리턴타입을 동일하게 선언한다.
	 * <p>
	 * - 효과
	 * 상속 받은 메서드와 이름, 인자가 같으므로 메서드 호출 방법이 기존의 코드와 동일하고
	 * 리턴타입이 동일하므로 호출 후 처리 방법이 기존의 코드와 동일하므로
	 * 기존의 코드를 수정하지 않고 변경된 내용을 반영할 수 있다.
	 * <p>
	 * <p>
	 * - 규칙
	 * 메서드 이름, 인자, 리턴타입이 같아야 한다.
	 */
	/*
	 * - 재정의에 의해 무시된 상속 받은 멤버(메서드, 속성)를 호출할 때 super를 통해 접근
	 *    super.메서드이름(~)       super.속성
	 */
	@Override // 규칙대로 함수를 정의하지 않으면 컴파일 에러 발생
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(", hobby=").append(hobby).append("]");
		return builder.toString();
	}
}