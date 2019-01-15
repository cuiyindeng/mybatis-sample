package com.exercise.config;

import com.exercise.principle.MyMapper;
import com.exercise.principle.MyMapperScanner;
import com.exercise.service.MyMapperFactoryBean;
import com.exercise.service.MyMapperTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用spring.factories读取配置类，可以不加@Configuration注解。
 */
public class MyAutoConfiguration {

    /**
     * Mapper的Bean和此Bean不能注入到同一个类中；否则会造成循环依赖。
     * @return
     */
    @Bean
    public MyMapperTemplate myMapperTempte() {
        return new MyMapperTemplate();
    }

    /**
     * 扫描并注册被自定义注解-@MyMapper修饰的Bean。
     */
    public static class MyAutoConfiguredMapperScannerRegistrar
            implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware {

        private BeanFactory beanFactory;

        private ResourceLoader resourceLoader;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            System.out.println("Searching for mappers annotated with @MyMapper");

            MyMapperScanner scanner = new MyMapperScanner(registry);
            try {
                if (this.resourceLoader != null) {
                    scanner.setResourceLoader(this.resourceLoader);
                }
                List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
                for (String pkg : packages) {
                    System.out.println("Using auto-configuration base package '" + pkg + "'");
                }
                scanner.setAnnotationClass(MyMapper.class);
                scanner.registerFilters();
                scanner.doScan(StringUtils.toStringArray(packages));
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }

        @Override
        public void setResourceLoader(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
        }
    }

    @Configuration
    @Import({MyAutoConfiguredMapperScannerRegistrar.class })
    @ConditionalOnMissingBean(MyMapperFactoryBean.class)
    public static class MyMapperScannerRegistrarNotFoundConfiguration implements InitializingBean {

        @Override
        public void afterPropertiesSet() {
            System.out.println("No " + MyMapperFactoryBean.class.getName() + " found.");
        }
    }
}
