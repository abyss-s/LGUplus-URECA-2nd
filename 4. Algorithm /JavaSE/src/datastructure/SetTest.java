package datastructure;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * 
 * Set 
 *  - 동일한 객체를 저장하지 않음  
 *    => 데이타의 유니크성을 보장 
 *  - add(Object o), contains(Object o), remove(Object o)에서 
 *    equals(Object o)와 hashCode()를  호출하여 객체가 같은지 비교한다.
 *  - 데이타를 저장한 순서대로 저장하지 않는다. 
 *  - index가 없다.  
 *  
 * TreeSet
 *  - set과 동일하게 유니크성을 보장하면서 tree를 이용해서 정렬을 유지한다. 
 *    ==> 정렬시 Comparable interface의 compareTo()를 이용하므로 
 *        저장할 객체에 Comparable interface 구현해야 된다.   
 */
public class SetTest {
	public static void main(String[] args) {
		HashSet<String> set1 = new HashSet<>();
		System.out.println(set1.add("hello"));
		System.out.println(set1.add("hello"));
		System.out.println(set1.add("world"));
		System.out.println(set1.add("ssafy"));
		System.out.println(set1);
		
		TreeSet<String> set2 = new TreeSet<>();
		System.out.println(set2.add("ssafy"));
		System.out.println(set2.add("hello"));
		System.out.println(set2.add("world"));
		
		for (String str : set2) {
			System.out.println(str);
		}
	}
}












