package cn.itcast.crm.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.crm.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerServiceTest {
	// 注入 接口代理对象
	@Autowired
	private CustomerService customerService ;

	@Test
	public void testFindNoAssociationCustomers() {
		System.out.println(customerService.findNoAssociationCustomers()); 
	}

	@Test
	public void testFindHasAssociationCustomers() {
		System.out.println(customerService.findHasAssociationCustomers("DQ001")); 
	}

	@Test
	public void testAssignCustomersToDecidedzone() {
		customerService.assignCustomersToDecidedzone(new Integer[]{2,3}, "DQ002");
	}

	@Test
	public void testFindDecidedZoneIdByAddress(){
		System.out.println(customerService.findDecidedZoneIdByAddress("北京中关村"));
	}
}
