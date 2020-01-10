package com.atguigu.springcloud.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController02
{
	@Autowired
	private DeptService service = null;

	@RequestMapping(value = "/dept/get02/{id}", method = RequestMethod.GET)
	//一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id)
	{

		Dept dept = this.service.get(id);
		
		if (null == dept) {
			throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
		}
		
		return dept;
	}
	//一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
		@HystrixCommand(fallbackMethod = "processHystrix_Getlist")
		@RequestMapping(value = "/dept/list02", method = RequestMethod.GET)
		public List<Dept> list()
		{
			return service.list();
		}

		public List<Dept> processHystrix_Getlist()
		{
			return Arrays.asList((new Dept().setDeptno(0L).setDname("没有信息,null--@HystrixCommand")
					.setDb_source("no this database in MySQL")));
		}
		public Dept processHystrix_Get(@PathVariable("id") Long id)
		{
			return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
					.setDb_source("no this database in MySQL");
		}
}
