package com.serfcompany.ecommerce.acart.view.main.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.presenter.main.CategoryFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author O.Drozdov
 *  Adapter for cardview categories list creating
 */
public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder> {


    private Context context;
    private List<Category> datas;
    private CategoryFragmentPresenter categoryFragmentPresenter;

    public CategoriesListAdapter(Context context, CategoryFragmentPresenter categoryFragmentPresenter){
        this.categoryFragmentPresenter = categoryFragmentPresenter;
        this.datas = new ArrayList<>();
        this.context = context;
    }

    public void setDatas(List<Category> datas){
        if (datas != null && datas.size() > 0){
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public Category getItem(int position){
        return datas.get(position);
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        final Category category = datas.get(position);
        holder.categoryName.setText(category.getName());
        Picasso.with(context)
                .load(category.getThumb())
                .fit()
                .centerInside()
                .placeholder(R.drawable.empty_photo)
                .error(R.drawable.default_product)
                .into(holder.categoryImage);
        holder.productsInCategory.setText("Products: " + category.getPostCount());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     *
     */
    public class CategoriesViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView categoryImage;
        TextView categoryName;
        TextView productsInCategory;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.categoryCardView);
            categoryImage = (ImageView) itemView.findViewById(R.id.category_item_CategoryImage);
            categoryName = (TextView) itemView.findViewById(R.id.category_item_CategoryName);
            productsInCategory = (TextView) itemView.findViewById(R.id.category_item_ProductCount);
        }
    }
}
