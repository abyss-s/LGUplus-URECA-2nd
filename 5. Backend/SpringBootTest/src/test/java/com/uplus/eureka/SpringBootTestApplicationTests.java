package com.uplus.eureka;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SpringBootTestApplicationTests {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DataSource ds;

	@Test
	public void dsTest() {
		log.info("Testing DataSource");
		assertNotNull(ds, "DataSource should not be null");
		log.info("DataSource is not null: {}", ds);
	}
}
