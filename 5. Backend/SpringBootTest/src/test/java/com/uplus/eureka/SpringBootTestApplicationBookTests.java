package com.uplus.eureka;

import com.uplus.eureka.book.model.dao.BookDao;
import com.uplus.eureka.book.model.dto.Book;
import com.uplus.eureka.book.model.service.BookService;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties"}
)
@ContextConfiguration(classes = SpringBootTestApplication.class) // 애플리케이션 설정 지정
@ComponentScan(basePackages = {"com.uplus.eureka.book"})
class SpringBootTestApplicationBookTests {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DataSource ds;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BookService service;

	@Test
	public void bookDaoTest() {
		//null 인지 체크하는 단정 함수
		assertNotNull(service);
	}

	@Test
	public void seacrchTest() throws Exception {
		Book book = bookDao.search("979-11");
		log.debug(book.toString());
	}

	@Test
	public void dsTest() {
		//null 인지 체크하는 단정 함수
		assertNotNull(ds);
	}
}
