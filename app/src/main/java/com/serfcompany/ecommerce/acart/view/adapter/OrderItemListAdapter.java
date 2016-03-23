package com.serfcompany.ecommerce.acart.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.model.orders.Item;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderItemListAdapter extends RecyclerView.Adapter<OrderItemListAdapter.OrderItemsViewHolder>{

    private Context context;
    private List<Item> orderItems;

    public OrderItemListAdapter(Context context, List<Item> orderItems){
        this.context = context;
        this.orderItems = new ArrayList<>(orderItems);
    }

    @Override
    public OrderItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_product, parent, false);
        return new OrderItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderItemsViewHolder holder, int position) {
        Item item = orderItems.get(position);
        holder.itemTitle.setText(item.getProductInfo().getProductName());
        holder.itemCost.setText(String.valueOf(new DecimalFormat("0.00").format(item.getProductPrice())));
        holder.itemAmount.setText(String.valueOf(item.getQuantity()));
        holder.itemTotalCost.setText(String.valueOf(new DecimalFormat("0.00").format(item.getTotalPrice())));
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context, ProductActivity.class);
//                intent.putExtra("Product", item.getProductName());
//                context.startActivity(intent);
//            }
//        });
        Picasso.with(context)
                .load(item.getProductInfo().getFeaturedImages())
                .fit()
                .centerInside()
                .placeholder(R.drawable.empty_photo)
                .error(R.drawable.default_product)
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class OrderItemsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView itemImage;
        TextView itemTitle;
        TextView itemCost;
        TextView itemAmount;
        TextView itemTotalCost;

        public OrderItemsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.order_itemCardView);
            itemImage = (ImageView) itemView.findViewById(R.id.order_itemProductImage);
            itemTitle = (TextView) itemView.findViewById(R.id.order_itemProductName);
            itemCost = (TextView) itemView.findViewById(R.id.order_itemProductCost);
            itemAmount = (TextView) itemView.findViewById(R.id.order_itemAmount);
            itemTotalCost = (TextView) itemView.findViewById(R.id.order_ItemTotalCost);

        }
    }
}
