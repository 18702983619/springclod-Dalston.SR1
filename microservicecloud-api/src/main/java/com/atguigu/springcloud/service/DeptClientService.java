package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.springcloud.entities.Dept;
/**
 * 
 * @author 123
 * 修改microservice-api工程，
 * 根据已经由的DeptClientService接口
 * 
 * 新建一个实现了FallbackFactory接口的类
 * DeptClientServiceFallbackFactory
 */
//@FeignClient(value="MICROSERVICECLOUD-DEPT")
@FeignClient(value="MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

	@RequestMapping(value = "/dept/add",method=RequestMethod.POST)
	public boolean add(Dept dept);
	
	@RequestMapping(value = "/dept/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id);

	@RequestMapping(value = "/dept/list",method=RequestMethod.GET)
	public List<Dept> list();

}
