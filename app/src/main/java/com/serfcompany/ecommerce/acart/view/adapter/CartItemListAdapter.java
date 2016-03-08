package com.serfcompany.ecommerce.acart.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.model.cart.CartItem;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by serfcompany on 08.03.16.
 */
public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.CartItemViewHolder>{

    private List<CartItem> cartItems;
    private Context context;

    public CartItemListAdapter(Context context, List<CartItem> cartItems){
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.productTitle.setText(String.valueOf(item.getId()));
        String currency = context.getSharedPreferences(
                Constants.CART_PREFS, Context.MODE_PRIVATE).getString(Constants.CURRENCY, "");
        holder.productPrice.setText(Html.fromHtml(currency+" "+item.getProductPrice()));
        holder.productQuantity.setText(item.getQuantity());
        holder.itemTotalPrice.setText(Html.fromHtml(currency + " " + item.getTotalPrice()));

//        Picasso.with(context)
//                .load("")
//                .fit()
//                .centerInside()
//                .placeholder(R.drawable.empty_photo)
//                .error(R.drawable.default_product)
//                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView productImage;
        TextView productTitle;
        TextView productPrice;
        TextView productQuantity;
        TextView itemTotalPrice;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cartItemCardView);
            productImage = (ImageView) itemView.findViewById(R.id.cartItemImage);
            productTitle = (TextView) itemView.findViewById(R.id.cartItemProductName);
            productPrice = (TextView) itemView.findViewById(R.id.cartItemProductCost);
            productQuantity = (TextView) itemView.findViewById(R.id.cartItemItemAmount);
            itemTotalPrice = (TextView) itemView.findViewById(R.id.cartItemTotalCost);
        }
    }
}
