package com.huaweicloud.frs.client.param;


import java.util.HashMap;
import java.util.Map;

public class CreateExternalFields {
    private Map<String, Object> externalFields;

    public CreateExternalFields() {
        externalFields = new HashMap<>();
    }

    public void addField(String key, FieldType type) {
        Map<String, Object> fieldType = new HashMap<>();

        if (type == FieldType.STRING) {
            fieldType.put("type", "string");
        } else if (type == FieldType.INTEGER) {
            fieldType.put("type", "integer");
        } else if (type == FieldType.FLOAT) {
            fieldType.put("type", "float");
        } else if (type == FieldType.DOUBLE) {
            fieldType.put("type", "double");
        } else if (type == FieldType.BOOLEAN) {
            fieldType.put("type", "boolean");
        } else if (type == FieldType.LONG) {
            fieldType.put("type", "long");
        }

        externalFields.put(key, fieldType);
    }

    public Map<String, Object> getExternalFields() {
        return externalFields;
    }
}
