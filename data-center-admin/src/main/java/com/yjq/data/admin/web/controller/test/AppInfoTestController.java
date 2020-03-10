package com.yjq.data.admin.web.controller.test;

import com.yjq.data.admin.model.domain.AppInfo;
import com.yjq.data.client.api.core.Page;
import com.yjq.data.client.api.core.ResponseMessage;
import com.yjq.data.client.api.manage.DataQueryManage;
import com.yjq.data.client.api.query.PageQuery;
import com.yjq.data.client.api.query.SimpleQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yejiaqian
 * @date Created in 2020/3/9 18:18 <br>
 */
@RestController
@RequestMapping("/app/test")
public class AppInfoTestController {

    @Resource
    private DataQueryManage<AppInfo> dataQueryManageImpl;

    @GetMapping("/get")
    public ResponseMessage<AppInfo> get(@Param("appId") Integer appId, @Param("sqlId") String sqlId) {
        return dataQueryManageImpl.queryForObject(new SimpleQuery(appId, sqlId, null), AppInfo.class);
    }

    @GetMapping("/list")
    public ResponseMessage<List<AppInfo>> list(@Param("appId") Integer appId, @Param("sqlId") String sqlId) {
        return dataQueryManageImpl.queryForList(new SimpleQuery(appId, sqlId, null), AppInfo.class);
    }
    @GetMapping("/count")
    public ResponseMessage<Long> count(@Param("appId") Integer appId, @Param("sqlId") String sqlId) {
        return dataQueryManageImpl.queryForCount(new SimpleQuery(appId, sqlId, null));
    }

    @GetMapping("/page")
    public ResponseMessage<Page<AppInfo>> page(@Param("appId") Integer appId, @Param("sqlId") String sqlId) {
        return dataQueryManageImpl.queryForPage(new PageQuery(appId, sqlId, null, 1, 10), AppInfo.class);
    }


}


