package com.yjq.data.service.config;

import com.alibaba.druid.pool.DruidDataSource;
import freemarker.cache.StringTemplateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * bean 配置
 * @author netyjq@163.com
 * @date 2017/6/8
 */
@Configuration
@EnableTransactionManagement
public class BeanConfig extends WebMvcConfigurerAdapter implements EnvironmentAware, ApplicationContextAware {

    private RelaxedPropertyResolver propertyResolver;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
        this.propertyResolver = new RelaxedPropertyResolver(env, "");
    }

    @Bean
    public MyFreemarkerConfig myFreemarkerConfig() {
        MyFreemarkerConfig cfg = new MyFreemarkerConfig(freemarker.template.Configuration.VERSION_2_3_0);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);
        cfg.setStringTemplateLoader(new StringTemplateLoader());
        return cfg;
    }


    /**
     * 注册DataSource
     * @param applicationContext ApplicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)
                configurableApplicationContext.getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(DruidDataSource.class);
        beanDefinitionBuilder.addPropertyValue("url", propertyResolver.getProperty("spring.datasource.url"));
        beanDefinitionBuilder.addPropertyValue("driverClassName", propertyResolver.getProperty("spring.datasource.driver-class-name"));
        beanDefinitionBuilder.addPropertyValue("username", propertyResolver.getProperty("spring.datasource.username"));
        beanDefinitionBuilder.addPropertyValue("password", propertyResolver.getProperty("spring.datasource.password"));
        beanDefinitionBuilder.setInitMethodName("init");
        beanDefinitionBuilder.setDestroyMethodName("close");
        defaultListableBeanFactory.registerBeanDefinition("dataSource", beanDefinitionBuilder.getRawBeanDefinition());
        logger.info(">>> DataSource init success.");
    }

}
