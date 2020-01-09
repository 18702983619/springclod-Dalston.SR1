package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.atguigu.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

/**
 * 千万不要忘记这个类上面添加@Component注解，大坑！！！
 * @author 123
 *
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	public DeptClientService create(Throwable arg0) {
		
		return new DeptClientService() {
			
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Dept get(Long id) {
				return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息，"
						+ "此刻服务provider已经关闭")
						.setDb_source("no this database in MySQL");

			}
			
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
