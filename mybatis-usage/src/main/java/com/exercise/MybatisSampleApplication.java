package com.exercise;

import com.exercise.mapper.MyUserMapper;
import com.exercise.mapper.UserMapper;
import com.exercise.model.User;
import com.exercise.model.Users;
import com.exercise.service.UserService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootApplication
/**
 *默认会扫描启动类所在的包，这样的话会将自定义的MyAutoConfig提前注册到spring容器中，
 * 从而导致MyAutoConfiguredMapperScannerRegistrar处理失败。
 */
@ComponentScan(basePackages = {
		"com.exercise.mapper",
		"com.exercise.service"
})
public class MybatisSampleApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

//	Mybatis的Mapper
//	private UserMapper userMapper;

//	自定义的Mapper。
//	private MyUserMapper userMapper;

//	构造方法注入
//	MybatisSampleApplication(UserMapper userMapper) {
//		this.userMapper = userMapper;
//	};

	public static void main(String[] args) {
		SpringApplication.run(MybatisSampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setAge(105);
//		user.setName("114");
//		System.out.println("Mapper的执行结果：" + userService.addUser(user));
		List<Users> usersList = userService.findAll();
		System.out.println("Mapper的执行结果：" + usersList);
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
		return new JPAQueryFactory(entityManager);
	}
}

