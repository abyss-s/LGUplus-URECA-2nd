package com.uplus.eureka.book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController  
 *   RestFul Service를 위한 Conrtoller 
 *   모든 메서드의 응답을  @ResponseBody를 붙여주는 효과
 */
@RestController	

//RestFul에서 서비스할 자원(Domain)명을 붙인다
@RequestMapping("/test")

/*
 * @CrossOrigin
 *  - CORS 요청에 대한 승인
 *  origins = {"*"}  : 요청하는 모든 URL, 메서드를 승인 
 *     ==> 보안에 취약하므로 상용에서는 사용 안함
 *     ==> 이후에 Configuration을 통해 설정할 예정  
 * */
@CrossOrigin(origins = {"*"})
public class TestController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * error 메세지 처리시 한글인 경우 깨지므로 한글 처리를 해야한다. 
	 * Content-Type : application/json;charset-UTF-8
	 * @ExceptionHandler
	 * 해당 컨트롤러에서 발생하는 오류를 처리하는 Annotation
	 */
	
	/**
	 * Get방식의 Form Data or Query String을 추출하는 방법
	 * 
	 * 1. 요청 데이타 1개를 추출하기 
	 *  @RequestParam(value='요청 데이타 명', required, defaultValaue) String or Primitive 
	 *  
	 *   value
	 *      - 추출할 요청 데이타 명을 지정
	 *     required
	 *        true : 요청 데이타가 없으면 error 발생 , default
	 *        false: 요청 데이타가 없어도 됨. 
	 *     defaultValue 
	 *        -  요청 데이타가 전달 되지 않는 경우 설정한 기본 값이 전달 된다.  
	 *        
	 * 2. 요청 데이타가 많은 경우 
	 *    2.1 @RequestParam Map
	 *     - request packet에 있는 모든 요청 데이타를 추출해서 Map에 저장해서 전달 된다.           
	 *     
	 *    2.2 DTO  ==> @ModelAttriute를 붙여도 되고 안붙여도 됨. 
	 *   	 - 지정한 DTO의 객체를 생성하고
	 *       객체에 선언된 모든 속성에 대해서 - 속성 이름으로 요청데이타를 추출 후 setter 함수로 객체에 setting
	 */
	
	/**
	 * ResponseEntity
	 * 응답 데이타를 담을 객체
	 */

	/**
	 * @PathVariable
	 * 요청 데이타가 url에 있는 경우 path에서 데이타를 전달 받을 때 사용하는 Annotation 
	 * Get 방식에서 범위 데이타를 요청할때와 pk를 이용한 1개의 데이타를 요청할때를 
	 * 구별하기 path로 전달된 pk를 추출할때 사용한다. 
	 */

	
	/**
	 * @RequestBody
	 * 요청 방식이 Put과 Post이면서 요청 데이타가 JOSON 형식일 때 
	 * 전달되는 요청 packet의 body를 객체로 전달 받을 때 사용하는 Annotation 
	 */
}
