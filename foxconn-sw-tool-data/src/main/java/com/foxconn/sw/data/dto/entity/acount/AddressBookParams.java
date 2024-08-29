package com.foxconn.sw.data.dto.entity.acount;

public class AddressBookParams {

    private int searchType;
    private String searchKey;

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
