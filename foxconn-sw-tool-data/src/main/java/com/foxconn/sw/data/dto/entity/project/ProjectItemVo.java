package com.foxconn.sw.data.dto.entity.project;

import com.foxconn.sw.data.dto.entity.KvPairs;

import java.util.Map;

public class ProjectItemVo {

    private Integer id;
    private KvPairs<Integer, Boolean> year;
//    private KvPairs<String, Boolean> projectCode;
//    private KvPairs<String, Boolean> customerName;
//    private KvPairs<String, Boolean> fullName;
//    private KvPairs<String, Boolean> manufacturingModel;
//    private KvPairs<String, Boolean> status;
//    private KvPairs<String, Boolean> rfqTime;
//    private KvPairs<String, Boolean> customer;
//    private KvPairs<String, Boolean> customerPartNO;
//    private KvPairs<String, Boolean> application;

    private Map<String, KvPairs<String, Boolean>> kvPairsMap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KvPairs<Integer, Boolean> getYear() {
        return year;
    }

    public void setYear(KvPairs<Integer, Boolean> year) {
        this.year = year;
    }

    public Map<String, KvPairs<String, Boolean>> getKvPairsMap() {
        return kvPairsMap;
    }

    public void setKvPairsMap(Map<String, KvPairs<String, Boolean>> kvPairsMap) {
        this.kvPairsMap = kvPairsMap;
    }
}
