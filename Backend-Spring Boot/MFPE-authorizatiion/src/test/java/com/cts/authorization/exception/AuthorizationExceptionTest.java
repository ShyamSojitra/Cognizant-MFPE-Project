package com.cts.authorization.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorizationExceptionTest {
	private AuthorizationException e = new AuthorizationException("message");
	@Test
	void testMessageSetter() {
		assertThat(e).isNotNull();
	}	
}
