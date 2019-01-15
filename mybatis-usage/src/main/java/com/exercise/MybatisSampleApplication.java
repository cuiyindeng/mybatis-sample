package com.exercise;

import com.exercise.mapper.MyUserMapper;
import com.exercise.mapper.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 *默认会扫描启动类所在的包，这样的话会将自定义的MyAutoConfig提前注册到spring容器中，
 * 从而导致MyAutoConfiguredMapperScannerRegistrar处理失败。
 */
@ComponentScan(basePackages = {
		"com.exercise.mapper"
})
public class MybatisSampleApplication implements CommandLineRunner {

	//Mybatis的Mapper
//	private UserMapper userMapper;

	//自定义的Mapper。
	private MyUserMapper userMapper;

	//构造方法注入
	MybatisSampleApplication(MyUserMapper userMapper) {
		this.userMapper = userMapper;
	};

	public static void main(String[] args) {
		SpringApplication.run(MybatisSampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Mapper的执行结果：" + userMapper.getAllUser());
	}
}

