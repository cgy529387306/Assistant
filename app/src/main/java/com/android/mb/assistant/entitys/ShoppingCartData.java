package com.android.mb.assistant.entitys;

public class ShoppingCartData {
    private String name;
    private boolean isSelect;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
