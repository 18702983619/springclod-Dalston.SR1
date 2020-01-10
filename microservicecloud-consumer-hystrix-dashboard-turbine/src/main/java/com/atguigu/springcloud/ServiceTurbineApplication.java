package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableHystrixDashboard //开启HystrixDashboard 图形化注解
@EnableTurbine	//使用了turbine后就可以监控多台服务器
@EnableEurekaClient
public class ServiceTurbineApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ServiceTurbineApplication.class, args);
	}
}
