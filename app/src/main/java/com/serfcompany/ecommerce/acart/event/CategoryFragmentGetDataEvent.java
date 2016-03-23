package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.category.Category;

import java.util.List;

public class CategoryFragmentGetDataEvent {
    List<Category> datas;

    public CategoryFragmentGetDataEvent(List<Category> datas){
        this.datas = datas;
    }

    public List<Category> getDatas() {
        return datas;
    }

    public void clearDatas(){
        datas.clear();
    }
}
