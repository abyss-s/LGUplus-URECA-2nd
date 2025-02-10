package chapter04;

public class CustomerTest {
	public static void main(String[] args) {
		Customer cust1 = new Customer();
		cust1.name = "UPlus";
		cust1.age = 2;
		cust1.address = "서울특별시 서초구 선릉역";

		Customer cust = new Customer("Uplus", "서울특별시 서초구 선릉역", 2);
		System.out.println(cust.toString());
		System.out.println(new Customer("kdg", "서울특별시 성북구", 37).toString());

		Customer cust2 = new Customer("1", "1", 1);
		System.out.println(cust1.equals(cust2));

	}
}
