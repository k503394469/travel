package cn.itcast.travel.domain;

import java.util.List;

public class Page<T> {
    private int pageSize;
    private int currentPage;
    private List<T> routeList;
    private int totalPage;
    private int totalCount;
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<T> routeList) {
        this.routeList = routeList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


}
