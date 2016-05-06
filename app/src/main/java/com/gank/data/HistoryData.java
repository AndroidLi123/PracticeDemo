package com.gank.data;

import java.util.List;

/**
 * Created by thinkpad on 2016/4/29.
 */
public class HistoryData extends BaseData{
    private List<String> results;

    public List<String> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "results=" + results.toString() +
                '}';
    }
}
