package cn.itcast.bos.activiti.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.utils.MD5Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ActivitiTest {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Test
	// 发布 
	public void testDeploy() throws IOException{
		DeploymentBuilder deployment = repositoryService.createDeployment();
		deployment.addZipInputStream(new ZipInputStream(new FileInputStream("holiday.zip")));
		deployment.deploy();
	}
	
	@Test
	// 启动流程
	public void testStart(){
		runtimeService.startProcessInstanceByKey("holiday");
	}
	
	@Test
	@Transactional
	@Rollback(false)
	// 关联实体变量
	public void testVariable(){
		// user还没有存在数据库
		User user = new User();
		user.setUsername("itcast");
		user.setPassword(MD5Utils.md5("itcast"));
		// JPA 保存实体
		entityManager.persist(user); 
		
		runtimeService.setVariable("2501", "user", user);
	}
	
	@Test
	public void testGetVariable(){
		// 先查询 ACT_RU_VARIABLE表 获取 实体类名和id  （Mybatis）
		// 再根据 类名和id 查询 user数据 （JPA ）
		User user = (User) runtimeService.getVariable("2501", "user");
		System.out.println(user); 
	}
}
