package com.equitasit.ms.emp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
class EmpMicroServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
