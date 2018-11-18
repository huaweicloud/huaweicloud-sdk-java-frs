package com.huaweicloud.frs.client.param;

import java.util.LinkedList;
import java.util.List;

public class SearchReturnFields {
    private List<String> returnFields;

    public SearchReturnFields() {
        returnFields = new LinkedList<>();
    }

    public void addReturnField(String key) {
        returnFields.add(key);
    }

    public List<String> getReturnFields() {
        return returnFields;
    }
}
