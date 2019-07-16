package com.android.mb.assistant.entitys;

/**
 * Created by cgy on 19/6/27.
 */

public class CommonListResp extends BaseResponse{

    private int pageIndex;
    private int pageSize;
    private boolean isLast;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isLast() {
        return pageIndex*pageSize>=getTotal();
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
