package com.serfcompany.ecommerce.acart.view.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.view.ProductActivity;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.model.product.Category;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.presenter.main.IExploreFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serfcompany on 29.02.16.
 */
public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductsViewHolder>{

    private Context context;
    private List<Product> datas;
    private IExploreFragmentPresenter exploreFragmentPresenter;

    public ProductsListAdapter(Context context, IExploreFragmentPresenter exploreFragmentPresenter) {
        this.exploreFragmentPresenter = exploreFragmentPresenter;
        this.datas = new ArrayList<>();
        this.context = context;
    }

    public ProductsListAdapter(Context context){
        this.datas = new ArrayList<>();
        this.context = context;
    }

    public void setDatas(List<Product> datas){
        if (datas != null && datas.size() > 0){
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public Product getItem(int position){
        return datas.get(position);
    }


    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, final int position) {
        final Product product = datas.get(position);
        holder.productTitle.setText(product.getGeneral().getTitle());
        holder.productDescription.setText(product.getGeneral().getContent().getExcepts());
        Boolean onSale = product.getGeneral().getPricing().getIsOnSale();
        String currency = product.getGeneral().getPricing().getCurrency();
        final String productRegularPrice = "" + currency+" "+String.valueOf(Double.valueOf(product.getGeneral().getPricing().getRegularPrice()));
        final String productSalePrice ="" + currency+" "+String.valueOf(product.getGeneral().getPricing().getSalePrice());
        if (!onSale){
            holder.onSaleImage.setImageDrawable(null);
            holder.productSalePrice.setText(null);
            holder.productRegularPrice.setText(Html.fromHtml(productRegularPrice));
            holder.productRegularPrice.setPaintFlags(holder.productRegularPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            holder.productRegularPrice.setText(Html.fromHtml(productRegularPrice));

            holder.productRegularPrice.setPaintFlags(holder.productRegularPrice
                    .getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.productSalePrice.setText(Html.fromHtml(productSalePrice));
            holder.onSaleImage.setImageResource(R.drawable.sale);
        }

        Picasso.with(context)
                .load(product.getProductGallery().getFeaturedImages())
                .fit()
                .centerInside()
                .placeholder(R.drawable.empty_photo)
                .error(R.drawable.default_product)
                .into(holder.productImage);


        List<Category> productCategories = product.getCategories();
        String productCategoryTag = "";
        for (Category category : productCategories){
            productCategoryTag += " "+category.getName();
        }
        holder.productCategory.setText(productCategoryTag);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ProductActivity.class);
                intent.putExtra("Product", product.getProductID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void animateTo(List<Product> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Product> newModels) {
        for (int i = datas.size() - 1; i >= 0; i--) {
            final Product model = datas.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Product> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Product model = newModels.get(i);
            if (!datas.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Product> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Product model = newModels.get(toPosition);
            final int fromPosition = datas.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Product removeItem(int position) {
        final Product model = datas.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Product model) {
        datas.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Product model = datas.remove(fromPosition);
        datas.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public List<Product> getDatas() {
        return datas;
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView productTitle;
        TextView productRegularPrice;
        TextView productSalePrice;
        TextView productDescription;
        TextView productCategory;
        ImageView productImage;
        ImageView onSaleImage;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            productTitle = (TextView) itemView.findViewById(R.id.title);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            productRegularPrice = (TextView) itemView.findViewById(R.id.productRegularPrice);
            productSalePrice = (TextView) itemView.findViewById(R.id.productSalePrice);
            onSaleImage = (ImageView) itemView.findViewById(R.id.saleImage);
            productDescription = (TextView) itemView.findViewById(R.id.productDescription);
            productCategory = (TextView) itemView.findViewById(R.id.productCategoryTag);
        }


    }
}
