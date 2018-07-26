package com.yunda.base;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.yunda.base.*.dao")
@SpringBootApplication
public class BootbaseApplication {
	private static final Logger LOGGER=LoggerFactory.getLogger(BootbaseApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BootbaseApplication.class, args);
		LOGGER.info("ヾ(◍°∇°◍)ﾉﾞ    yunda.base启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}
}
