package com.huaweicloud.frs.client.param;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchSort {
    private List<Map<String, Object>> searchSort;

    public SearchSort() {
        searchSort = new ArrayList<>();
    }

    public void addSortField(String key, SortType sortType) {
        Map<String, Object> sortField = new HashMap<>();
        if (SortType.ASC == sortType) {
            sortField.put(key, "asc");
        } else {
            sortField.put(key, "desc");
        }
        searchSort.add(sortField);
    }

    public List<Map<String, Object>> getSearchSort() {
        return searchSort;
    }
}
