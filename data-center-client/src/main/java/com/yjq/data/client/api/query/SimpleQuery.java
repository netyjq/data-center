package com.yjq.data.client.api.query;

import lombok.Data;

import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019/4/24
 */
@Data
public class SimpleQuery extends AbstractQuery {

    public SimpleQuery(Integer appId, String sqlId, Map<String, Object> filters) {
        super(appId, sqlId, filters);
    }

    @Override
    public boolean invalid() {
        if (super.getAppId() == null || super.getSqlId() == null) {
            return true;
        }
        return false;
    }
}
