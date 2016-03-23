package com.serfcompany.ecommerce.acart.view.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.HTTPHolders.GetProductByIdHTTP;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.model.cart.Item;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.parser.SingleProductParser;
import com.serfcompany.ecommerce.acart.presenter.CartActivityPresenter;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.CartItemViewHolder>{

    private List<Item> cartItems;
    private Product product;
    private Context context;
    SharedPreferences cartPrefs;
    SharedPreferences.Editor cartPrefsEditor;

    public CartItemListAdapter(Context context, List<Item> cartItems){
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item_cart, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartItemViewHolder holder, int position) {
        final Item item = cartItems.get(position);
        product = new Product();
        cartPrefs = context.getSharedPreferences(Constants.CART_PREFS, Context.MODE_PRIVATE);
        cartPrefsEditor = cartPrefs.edit();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    GetProductByIdHTTP con = new GetProductByIdHTTP();
                    SingleProductParser parser = new SingleProductParser();
                    product = parser.parseSingleProduct(con.loadProductByID(item.getId()));
                } catch (Exception e){

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                holder.productTitle.setText(String.valueOf(product.getGeneral().getTitle()));
                Picasso.with(context)
                        .load(product.getProductGallery().getFeaturedImages())
                        .fit()
                        .centerInside()
                        .placeholder(R.drawable.empty_photo)
                        .error(R.drawable.default_product)
                        .into(holder.productImage);
                int quantity = 0;
                if (product.getInventory().getQuantity() != null) {
                    quantity = product.getInventory().getQuantity();
                }
                List<String> arr = new ArrayList<>();
                arr.add(0, "");
                for (int i = 0; i < quantity; i++){
                    arr.add(i+1, String.valueOf(i));
                }
                ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(context,
                        android.R.layout.simple_spinner_item, arr);
                quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.spinner.setAdapter(quantityAdapter);
            }
        }.execute();

        String currency = context.getSharedPreferences(
                Constants.CART_PREFS, Context.MODE_PRIVATE).getString(Constants.CURRENCY, "");
        holder.productPrice.setText(Html.fromHtml(currency + " " + String.valueOf(new DecimalFormat("0.00").format(item.getProductPrice()))));

        holder.productQuantity.setText(item.getQuantity());
        holder.itemTotalPrice.setText(Html.fromHtml(currency + " " + String.valueOf(new DecimalFormat("0.00").format(item.getTotalPrice()))));

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                holder.spinner.setSelected(false);
                if (position > 0) {
                    if (id == 1) {
                        cartPrefsEditor.remove(String.valueOf(item.getId()));
                        cartPrefsEditor.apply();
                    } else {
                        cartPrefsEditor.putString(String.valueOf(item.getId()), String.valueOf(position-1));
                    }
                    cartPrefsEditor.apply();
                    SharedPreferences couponPrefs = context.getSharedPreferences(Constants.COUPON_PREFS, Context.MODE_PRIVATE);
                    Map<String, String> coupons = new HashMap<>();
                    coupons.put("coupon", couponPrefs.getString("coupon", null));
                    SharedPreferences loginPrefs = context.getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE);
                    Map<String, String> products;
                    products = (Map<String, String>) cartPrefs.getAll();
                    products.remove(Constants.CURRENCY);
                    CartActivityPresenter cartActivityPresenter = new CartActivityPresenter(context);
                    cartActivityPresenter.checkCart(loginPrefs.getString(Constants.USERNAME, ""),
                            loginPrefs.getString(Constants.PASSWORD, ""), products, coupons);
                    holder.spinner.setVisibility(View.GONE);
                    holder.productQuantity.setText(String.valueOf(position-1));
                    holder.productQuantity.setVisibility(View.VISIBLE);
                    holder.quantityHint.setVisibility(View.VISIBLE);
                }else {
//                    cartPrefsEditor.remove(String.valueOf(item.getId()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.quantityHint.setVisibility(View.GONE);
                holder.productQuantity.setVisibility(View.GONE);
                holder.spinner.setVisibility(View.VISIBLE);
            }
        });

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
        TextView quantityHint;
        Spinner spinner;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cartItemCardView);
            productImage = (ImageView) itemView.findViewById(R.id.cartItemImage);
            productTitle = (TextView) itemView.findViewById(R.id.cartItemProductName);
            productPrice = (TextView) itemView.findViewById(R.id.cartItemProductCost);
            productQuantity = (TextView) itemView.findViewById(R.id.cartItemItemAmount);
            itemTotalPrice = (TextView) itemView.findViewById(R.id.cartItemTotalCost);
            quantityHint = (TextView) itemView.findViewById(R.id.cartItemItemAmountHint);
            spinner = (Spinner) itemView.findViewById(R.id.quantitySpinner);
        }
    }
}
