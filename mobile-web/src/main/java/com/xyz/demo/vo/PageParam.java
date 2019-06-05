package com.xyz.demo.vo;

public class PageParam {
    private Integer pageNo;
    private Integer pageSize;


    public Integer getPageNo() {
        if(pageNo == null){
            return 1;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if(pageSize == null){
            return 5;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageParam(Integer pageNo, Integer pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
