package com.serfcompany.ecommerce.acart.view;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.ProductByIdEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.presenter.main.ProductActivityPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by serfcompany on 08.02.16.
 */
public class ProductActivity extends AbstractActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ProductActivityPresenter presenter;
    private SharedPreferences cartPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        cartPreferences = getSharedPreferences(Constants.CART_PREFS, MODE_PRIVATE);
        editor = cartPreferences.edit();

        int id = getIntent().getIntExtra("Product", 0);
        presenter = new ProductActivityPresenter(this);
        presenter.loadProduct(id);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.product_drawer_layout);

        initToolbar();
        initNavigationView(drawerLayout, null);
    }

    private void fillFields(final Product product) {

        setTitle(product.getGeneral().getTitle());

        TextView productTitle = (TextView) findViewById(R.id.productPageTitle);
        ImageView productImage = (ImageView) findViewById(R.id.productActivityImage);
        ImageView onSaleImage = (ImageView) findViewById(R.id.productPageSaleImage);
        TextView regularPrice = (TextView) findViewById(R.id.productPageRegularPrice);
        TextView salePrice = (TextView) findViewById(R.id.productPageSalePrice);
        TextView inStock = (TextView) findViewById(R.id.productsInStock);
        final Spinner quantitySpinner = (Spinner) findViewById(R.id.quantitySpinner);
        Button addToCartButton = (Button) findViewById(R.id.productPageAddToCardButton);

        String currency = product.getGeneral().getPricing().getCurrency();
        String regPriceString = String.valueOf("" + currency + " " + product.getGeneral().getPricing().getRegularPrice());
        String salePriceString = String.valueOf(""+currency+" "+product.getGeneral().getPricing().getSalePrice());

        Picasso.with(this)
                .load(product.getProductGallery().getFeaturedImages())
                .fit()
                .centerInside()
                .placeholder(R.drawable.default_product)
                .error(R.drawable.default_product)
                .into(productImage);

        if (product.getGeneral().getPricing().getIsOnSale()){
            regularPrice.setText(Html.fromHtml(regPriceString));
            regularPrice.setPaintFlags(regularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            salePrice.setText(Html.fromHtml(salePriceString));
            onSaleImage.setImageResource(R.drawable.sale);
        } else {
            regularPrice.setText(Html.fromHtml(regPriceString));
        }
        productTitle.setText(product.getGeneral().getTitle());
        final int quantity = product.getInventory().getQuantity();
        inStock.setText(String.valueOf(quantity)+" in stock");
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < quantity; i++){
            arr.add(i, i+1);
        }
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arr);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);


        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = product.getProductID();
                int quatity = (int) (quantitySpinner.getSelectedItemId()+1);
                String checkQuantity = cartPreferences.getString(String.valueOf(id), null);
                if (cartPreferences.getString(Constants.CURRENCY, null) == null){
                    editor.putString(Constants.CURRENCY, product.getGeneral().getPricing().getCurrency());
                    editor.commit();
                }
                if (checkQuantity != null){
                    editor.putString(String.valueOf(id), String.valueOf(Integer.parseInt(checkQuantity)+quatity));
                    Toast.makeText(getBaseContext(), product.getGeneral().getTitle()+" was added to cart",
                            Toast.LENGTH_SHORT).show();
                    Log.i("LOG", "Product #"+id+" quantity was increased to "+ checkQuantity +" + " + quatity);
                    editor.commit();
                } else {
                    editor.putString(String.valueOf(id), String.valueOf(quatity));
                    Toast.makeText(getBaseContext(), product.getGeneral().getTitle()+" was added to cart",
                            Toast.LENGTH_SHORT).show();
                    Log.i("LOG", "Product #" + id + " was added with quantity " + quatity);
                    editor.commit();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    // EventBus Subscribe
    public void onEvent(ProductByIdEvent getDatasEvent){
        if (getDatasEvent!=null && getDatasEvent.getProduct()!=null){
            fillFields(getDatasEvent.getProduct());
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.productToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
