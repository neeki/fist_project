package cn.itcast.bos.service.user.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.user.UserService;
import cn.itcast.bos.utils.MD5Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserServiceTest {
	@Autowired
	private UserService userService; 
	
	@Test
	public void testSave(){
		User user = new User();
		user.setUsername("admin");
		user.setPassword(MD5Utils.md5("admin"));
		
		userService.saveUser(user);
	}
	
	@Test
	public void testFindAll(){
		System.out.println(userService.findAllUsers());
	}
	
	@Test
	public void testLogin(){
		System.out.println(userService.findByUsername("admin"));
		
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		System.out.println(userService.login(user));
	}
	
	@Test
	public void testFindPassword(){
		System.out.println(userService.findPasswordByUsername("admin"));
	}
}
