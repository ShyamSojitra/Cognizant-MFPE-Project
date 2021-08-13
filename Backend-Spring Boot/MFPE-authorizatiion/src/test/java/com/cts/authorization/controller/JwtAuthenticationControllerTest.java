package com.cts.authorization.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.authorization.config.JwtTokenUtil;
import com.cts.authorization.model.JwtRequest;
import com.cts.authorization.model.User;
import com.cts.authorization.service.JwtUserDetailsService;

@SpringBootTest
class JwtAuthenticationControllerTest {

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private JwtUserDetailsService userDetailsService;
	
	@InjectMocks
	private JwtAuthenticationController controller;
	
	@Test
	void testGenerateToken() throws Exception {
		JwtRequest req = new JwtRequest("admin","pass");
		User user = new User(1,"admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.generateToken(details)).thenReturn("token");
		String entity = controller.createAuthenticationToken(req);
		//assertThat(Integer.valueOf(entity.getStatusCodeValue()).equals(Integer.valueOf(200))).isTrue();

	}
	
	@Test
	public void testAuthorization() throws Exception {
		when(jwtTokenUtil.getUsernameFromToken("Bearer token")).thenReturn(null);
		assertThat(controller.authorizeTheRequest("Bearer token")).isFalse();
	}
	
	@Test
	public void testAuthorizationInvalid() throws Exception {
		User user = new User(1,"admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		assertThat(controller.authorizeTheRequest("token")).isFalse();

	}
	
	@Test
	public void testAuthorizationNullUser() throws Exception {

		User user = new User(1,"admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		
		assertThat(controller.authorizeTheRequest("WrongToken")).isFalse();

	}
}
