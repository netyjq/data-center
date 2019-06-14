package com.yjq.data.test;

import com.yjq.data.admin.AdminBootstrap;
import com.yjq.data.client.api.core.DataQueryInvoker;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.query.SimpleQuery;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
@SpringBootTest(classes = AdminBootstrap.class)
@RunWith(SpringRunner.class)
public class DataQueryInvokerTest {

    @Autowired
    private DataQueryInvoker dataQueryInvoker;

    @Test
    public void run() throws IOException, TemplateException {
        Map<String,Object> filters = new HashMap<>();
        SimpleQuery simpleQuery = new SimpleQuery(1, "xxx", filters);
        ResponseMessage<List<LinkedHashMap>> response = dataQueryInvoker.queryForList(simpleQuery);
        System.out.println(response.getObject());
    }

}
