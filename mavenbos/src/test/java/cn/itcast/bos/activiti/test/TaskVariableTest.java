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
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
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
public class TaskVariableTest {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private IdentityService identityService;
	
	@Test
	// 通过task对象，是否能够直接获取变量
	public void testTaskVariables(){
		Task task = taskService.createTaskQuery().taskId("15005").singleResult();
		TaskEntity taskEntity = (TaskEntity) task;
		System.out.println(taskEntity.getVariableLocal("zhongzhuaninfo")); 
	}
	

}
