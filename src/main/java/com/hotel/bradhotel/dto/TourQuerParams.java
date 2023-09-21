package com.hotel.bradhotel.dto;

import com.hotel.bradhotel.constant.TourCity;

//因為查詢的條件可能會很多，新增個class管理所有的查詢條件參數，不用一個一個填寫
public class TourQuerParams {

    TourCity city;
    String  search;
    String  orderBy;
    String  sort;
    Integer limit;
    Integer offset;

    public TourCity getCity() {
        return city;
    }

    public void setCity(TourCity city) {
        this.city = city;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
