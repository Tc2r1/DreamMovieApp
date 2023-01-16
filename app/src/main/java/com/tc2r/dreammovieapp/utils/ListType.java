package com.tc2r.dreammovieapp.utils;

public enum ListType {
    POPULAR(1), SEARCH(2);

    private final int viewType;

    public int getViewType() {
        return viewType;
    }

    ListType(int viewType) {
        this.viewType = viewType;
    }
}
