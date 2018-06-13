package com.nextyu.mall.query;

/**
 * created on 2017-06-05 14:48
 *
 * @author nextyu
 */
public class BaseQuery {
    private Integer pageNum = 0; // 页码
    private Integer pageSize = 10;// 每页显示数量

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
