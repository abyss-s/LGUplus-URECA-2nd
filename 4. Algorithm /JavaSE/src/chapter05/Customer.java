package chapter05;
public class Customer {
	private String name;
	private int age;
	private String address;
	public Customer() {
		this("UPlus", 2, "서울 강남구 선릉로" );
	}
	public Customer(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address =address;
	}
	public boolean equals(Customer cust) {
		return this == cust;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [name=").append(name).append(", age=").append(age).append(", address=").append(address)
				.append("]");
		return builder.toString();
	}
}






