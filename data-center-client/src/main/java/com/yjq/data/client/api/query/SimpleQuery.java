package com.yjq.data.client.api.query;

import java.util.Collections;
import java.util.Map;

/**
 * @author netyjq@gmail.com
 * @date 2019/4/24
 */
public class SimpleQuery extends AbstractQuery {

    public SimpleQuery(Integer appId, String sqlId, Map<String, Object> filters) {
        super(appId, sqlId, filters);
    }

    public SimpleQuery(Integer appId, String sqlId) {
        super(appId, sqlId, Collections.<String, Object>emptyMap());
    }

    public SimpleQuery() {
    }

    @Override
    public boolean invalid() {
        if (super.getAppId() == null || super.getSqlId() == null) {
            return true;
        }
        return false;
    }
}
