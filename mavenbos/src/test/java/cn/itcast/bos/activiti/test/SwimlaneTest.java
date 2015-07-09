package cn.itcast.bos.activiti.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.activiti.engine.IdentityService;
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
public class SwimlaneTest {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private IdentityService identityService;
	
	@Test
	// 发布 
	public void testDeploy() throws IOException{
		DeploymentBuilder deployment = repositoryService.createDeployment();
		deployment.addClasspathResource("baoxiao.bpmn");
		deployment.deploy();
	}
	
	@Test
	// 启动流程
	public void testStart(){
		// 设置登录用户
		identityService.setAuthenticatedUserId("小丽");
		// 启动流程 activiti:initiator="applyUser" ，自动将当期登录用户 存入 applyUser 变量
		runtimeService.startProcessInstanceByKey("baoxiao");
	}
	
	@Test
	// 完成任务
	public void complete(){
		taskService.complete("5002");
	}
	

}
