package cn.itcast.bos.proxy.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import cn.itcast.bos.web.action.bc.SubareaAction;

public class ProxyDITest {
	@Test
	public void testDI(){
		final SubareaAction target= new SubareaAction();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new org.springframework.cglib.proxy.MethodInterceptor() {
			public Object intercept(Object arg0, Method arg1, Object[] arg2, org.springframework.cglib.proxy.MethodProxy arg3) throws Throwable {
				return null;
			}
		});
		Object proxy = enhancer.create();
		Field[] declaredFields = proxy.getClass().getDeclaredFields();
		Method[] declaredMethods = proxy.getClass().getDeclaredMethods();
		
		System.out.println(declaredFields);
		System.out.println(declaredMethods);
	}
}
