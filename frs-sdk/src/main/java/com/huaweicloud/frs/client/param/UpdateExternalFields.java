package com.huaweicloud.frs.client.param;

import java.util.HashMap;
import java.util.Map;

public class UpdateExternalFields {

    private Map<String, Object> externalFields;

    public UpdateExternalFields() {
        externalFields = new HashMap<>();
    }

    public void addField(String key, Object value) {
        externalFields.put(key, value);
    }

    public Map<String, Object> getExternalFields() {
        return externalFields;
    }

}
