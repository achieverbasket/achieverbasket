//package com.ab.dao;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.ab.spring.application.config.SpringApp;
//import com.ab.spring.dao.LoginDao;
//import com.ab.type.UserType;
//import com.ab.vo.User;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringApp.class)
//@WebAppConfiguration
//public class LoginDaoTest {
//
//	@Autowired
//	private LoginDao loginDao;
//	
//	@Test
//	public void testRegisterUser() {
//		User user = new User();
//		user.setEmail("a@gmail.com");
//		user.setFirstName("A");
//		user.setHintA("Hint A");
//		user.setHintQ("Hint Q");
//		user.setLastName("B");
//		user.setPassword("abcd");
//		user.setUserName("AB");
//		user.setUserType(UserType.CANDIDATE);
//		
//		System.out.println(loginDao.registerUser(user));
//	}
//}
