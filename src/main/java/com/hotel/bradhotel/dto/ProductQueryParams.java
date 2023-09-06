package com.hotel.bradhotel.dto;

import com.hotel.bradhotel.constant.ProductCategory;

//因為查詢的條件可能會很多，新增個class管理所有的查詢條件參數，不用一個一個填寫
public class ProductQueryParams {

    ProductCategory category;
    String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
