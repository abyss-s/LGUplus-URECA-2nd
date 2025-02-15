package datastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import chapter06.Employee;

/**
 * Map
 *  - 유일한 key값으로 value를 관리하는  자료구조 
 *  - 검색이 가장 빠른 자료구조 
 *  
 */
public class MapTest {
	public static void main(String[] args) {
		HashMap<Integer, Object> map1 = new HashMap<>();
//		put(key, value)
		map1.put(1, "hello");
		map1.put(2, 256);
		map1.put(3, new Employee("1", "ssafy", 3500000));
		map1.put(1, "world"); //같은 key로 다른 value를 저장하면 덮어쓰기 됨.
		System.out.println(map1);
//		get(key) 
		System.out.println(map1.get(1));
		
//		remove(key)  :key에 해당하는 객체를 삭제하고 삭제한 데이타를 리턴한다. 
		
		
		//keySet()   : map에 저장된 key값만 추출 

		//values()	 : map에 저장된 value값만  추출
	}
}


















