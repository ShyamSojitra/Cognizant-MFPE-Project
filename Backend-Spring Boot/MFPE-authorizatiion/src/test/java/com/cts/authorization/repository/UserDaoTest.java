package com.cts.authorization.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.authorization.model.User;

@DataJpaTest
class UserDaoTest {

	@MockBean
	private UserDao dao;
	
	@Test
	void testUserDaoFindByName() {
		User user = new User(1,"admin", "password");
		when(dao.findByUserName("admin")).thenReturn(user);
		assertThat(dao.findByUserName("admin").equals(user));
	}

}
