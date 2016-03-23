package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.List;

public class CategoryExploreEvent {
    List<Category> categories;
    List<Product> categoriesProducts;

    public CategoryExploreEvent(List<Category> categories, List<Product> categoriesProducts){
        this.categories = categories;
        this.categoriesProducts = categoriesProducts;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getCategoriesProducts() {
        return categoriesProducts;
    }
}
