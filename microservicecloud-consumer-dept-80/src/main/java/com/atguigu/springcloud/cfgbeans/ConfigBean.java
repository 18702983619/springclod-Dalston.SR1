package com.atguigu.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.atguigu.myrule.RandomRule_ZY;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{ 
	@Bean //默认轮询
	@LoadBalanced //spring cloud ribbon 实现的一套客户端，负载均衡的工具
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@Bean //默认springcloud自带的有7种规则
	public IRule myRule(){
		
		//return new RandomRule();//随机规则
		/**
		 * 会先过滤掉由于多次访问故障而处于短路器跳闸状态的服务
		 * 还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
		 */
		
		return new RetryRule();
		
	}
}
