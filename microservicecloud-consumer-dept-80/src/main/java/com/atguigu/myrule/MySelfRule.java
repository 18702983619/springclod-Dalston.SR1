package com.atguigu.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
//@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
/**
 * 
 * 官方文档明确给出警告：
 * 这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
 * 否则我们自定义的这个配置类就会被所有的ribbon客户端所共享，也就是说
 * 我们达不到特殊化定制的目的了
 *
 */
@Configuration
public class MySelfRule {

	@Bean //默认springcloud自带的有7种规则
	public IRule myRule(){
		
		//return new RandomRule();//随机规则
		/**
		 * 会先过滤掉由于多次访问故障而处于短路器跳闸状态的服务
		 * 还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
		 */
		
		//return new RetryRule();
		return new RandomRule_ZY();//自定义的
	}
}
