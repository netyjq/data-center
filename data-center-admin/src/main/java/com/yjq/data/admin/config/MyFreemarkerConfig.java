package com.yjq.data.admin.config;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
/**
 * @author netyjq@gmail.com
 * @date 2019/4/24
 */
public class MyFreemarkerConfig extends Configuration {

    private StringTemplateLoader stringTemplateLoader;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public MyFreemarkerConfig(Version incompatibleImprovements) {
        super(incompatibleImprovements);
    }
    /**
     * 渲染解析sql
     * @param templateId 模板id --> sqlId
     * @param templateText 模板内容 --> sqlText
     * @param model 模型 --> param
     * @return the sql that can be executed by mybatis
     */
    public String render(String templateId, String templateText, Map<String,Object> model) {
        Writer out = new StringWriter();
        try {
            stringTemplateLoader.putTemplate(templateId, templateText);
            this.setTemplateLoader(stringTemplateLoader);
            Template template = this.getTemplate(templateId);
            logger.info("get template, name: {}, hashcode: {}", templateId, template.hashCode());
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sql = out.toString();
        return sql;
    }
    public StringTemplateLoader getStringTemplateLoader() {
        return stringTemplateLoader;
    }
    public void setStringTemplateLoader(StringTemplateLoader stringTemplateLoader) {
        this.stringTemplateLoader = stringTemplateLoader;
        this.setTemplateLoader(stringTemplateLoader);
    }
}
