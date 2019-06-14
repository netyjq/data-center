//package com.yjq.data.test;
//
//import com.yjq.data.admin.AdminBootstrap;
//import org.beetl.core.Configuration;
//import org.beetl.core.GroupTemplate;
//import org.beetl.core.Template;
//import org.beetl.core.resource.StringTemplateResourceLoader;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author netyjq@gmail.com
// * @date 2019-05-15
// */
////@SpringBootTest(classes = AdminBootstrap.class)
////@RunWith(SpringRunner.class)
//public class BeetlTest {
//
//
//    public static void main(String args[]) {
//        //new一个模板资源加载器
//        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
//        /* 使用Beetl默认的配置。
//         * Beetl可以使用配置文件的方式去配置，但由于此处是直接上手的例子，
//         * 我们不去管配置的问题，只需要基本的默认配置就可以了。
//         */
//        Configuration config = null;
//        try {
//            config = Configuration.defaultConfiguration();
////            Properties properties = new Properties();
////            properties.put("DELIMITER_STATEMENT_START", "<#");
////            properties.put("DELIMITER_STATEMENT_END", "</#>");
////            config.setPs(properties);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //Beetl的核心GroupTemplate
//        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, config);
//        //我们自定义的模板，其中${title}就Beetl默认的占位符
//        String testTemplate="select * from app_info where 1=1 <%if(${name}!=null && ${name} != '') {%> and name = #{name} <%}%>";
//        Template template = groupTemplate.getTemplate(testTemplate);
//        Map<String,Object> map = new HashMap<>();
//        map.put("name", "统一接入");
//        //渲染字符串
//        template.binding(map);
//        String str = template.render();
//        System.out.println(str);
//    }
//
//}
