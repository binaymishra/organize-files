package com.bm.organize;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
class ApplicationTest {

	@Value("${input.path}")
	private String inputPath;

	@Test
	void contextLoads() {

	}

	@Test
	public void assertInputPath() {
		Assert.assertNotNull(inputPath);
	}

}
