package com.uplus.eureka;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import com.uplus.eureka.member.model.dto.Member;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties"}
)
/*
 *
 * Spring Boot 3.x에서는 @SpringBootTest만으로 Bean 설정이 자동 감지되지 않을 수도 있습니다.
 *  @ContextConfiguration을 사용하여 명시적으로 설정 파일을 지정해 보세요.
 */
@ContextConfiguration(classes = SpringBootTestApplication.class)  // 설정 클래스를 명시적으로 지정
@ComponentScan(basePackages = {"com.uplus.eureka"})
class SpringBootTestApplicationMemberTests {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DataSource ds;

	@Autowired
	private MemberDaoImp memberDao;

	@Test
	public void memberDaoTest() {
		// null 인지 체크하는 단정 함수
		assertNotNull(memberDao);
	}

	@Test
	public void seacrchTest() throws Exception {
		Member member = memberDao.search("eureka");
		log.debug(member.toString());
	}

	@Test
	public void dsTest() {
		// null 인지 체크하는 단정 함수
		assertNotNull(ds);
	}
}
