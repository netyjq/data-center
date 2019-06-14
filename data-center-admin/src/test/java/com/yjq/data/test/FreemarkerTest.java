package com.yjq.data.test;

import com.yjq.data.admin.config.MyFreemarkerConfig;
import freemarker.cache.StringTemplateLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-16
 */
public class FreemarkerTest {

    public static void main(String args[]) {
        MyFreemarkerConfig cfg = new MyFreemarkerConfig(freemarker.template.Configuration.VERSION_2_3_0);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);
        cfg.setStringTemplateLoader(new StringTemplateLoader());
        String templateStr = "select * from app_info where 1=1 <#if name?? && name != ''>and name = ${r'#{'}name}</#if>";
        templateStr = "select * from app_info where 1=1 <#if name?? && name != ''>and name = <#noparse>#{name}</noparse></#if>";
        Map<String,Object> map = new HashMap<>();
        map.put("name", "name");
        String result = cfg.render("11", templateStr, map);
        System.out.println(result);
    }

}
