package com.serfcompany.ecommerce.acart.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.event.ProductByIdEvent;
import com.serfcompany.ecommerce.acart.model.product.Product;
import com.serfcompany.ecommerce.acart.presenter.main.ProductActivityPresenter;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ProductActivity extends AbstractActivity {

    private Toolbar toolbar;
    private ProductActivityPresenter presenter;
    private SharedPreferences cartPreferences;
    SharedPreferences.Editor editor;
    FrameLayout loadingFrame;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    ImageView productImage;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_product);

        cartPreferences = getSharedPreferences(Constants.CART_PREFS, MODE_PRIVATE);
        editor = cartPreferences.edit();

        int id = getIntent().getIntExtra("Product", 0);
        presenter = new ProductActivityPresenter(this);
        presenter.loadProduct(id);

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        loadingFrame = (FrameLayout) findViewById(R.id.progressBarFrame);

        productImage = (ImageView) findViewById(R.id.productActivityImage);
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(productImage, product);
            }
        });


        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        LinearLayout toComments = (LinearLayout) findViewById(R.id.toCommentsActivity);
        toComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CommentsActivity.class);
                startActivity(intent);
            }
        });

        initToolbar();
    }

    private void fillFields(final Product product) {

        setTitle(product.getGeneral().getTitle());

        TextView productTitle = (TextView) findViewById(R.id.productPageTitle);

        ImageView onSaleImage = (ImageView) findViewById(R.id.productPageSaleImage);
        TextView regularPrice = (TextView) findViewById(R.id.productPageRegularPrice);
        TextView salePrice = (TextView) findViewById(R.id.productPageSalePrice);
        TextView productDescription = (TextView) findViewById(R.id.productPageDescription);
        TextView productRating = (TextView) findViewById(R.id.productPageRating);
        TextView productVotes = (TextView) findViewById(R.id.productPageVotesCount);
//        TextView inStock = (TextView) findViewById(R.id.productsInStock);
        final Spinner quantitySpinner = (Spinner) findViewById(R.id.quantitySpinner);
        Button addToCartButton = (Button) findViewById(R.id.productPageAddToCardButton);



        String currency = product.getGeneral().getPricing().getCurrency();
        Double regPrice = Double.parseDouble(product.getGeneral().getPricing().getRegularPrice());
        Double salPrice =0.0;
        if (product.getGeneral().getPricing().getIsOnSale()){
            salPrice = Double.parseDouble(product.getGeneral().getPricing().getSalePrice());
        }else{
            salePrice.setVisibility(View.GONE);
        }
        String regPriceString = String.valueOf("" + currency + " " + String.valueOf(new DecimalFormat("0.00").format(regPrice)));
        String salePriceString = String.valueOf(""+currency+" "+String.valueOf(new DecimalFormat("0.00").format(salPrice)));
        productDescription.setText(product.getGeneral().getContent().getExcepts());
        productRating.setText(product.getRatings().getAverageRating());
        productVotes.setText(" ("+String.valueOf(product.getRatings().getRatingCount())+" votes)");


        Picasso.with(this)
                .load(product.getProductGallery().getFeaturedImages())
                .fit()
                .centerInside()
                .placeholder(R.drawable.empty_photo)
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
        int quantity = 0;
        productTitle.setText(product.getGeneral().getTitle());
        if (product.getInventory().getQuantity() != null) {
            quantity = product.getInventory().getQuantity();
//            inStock.setText(String.valueOf(quantity)+" in stock");
        } else {
//            inStock.setText("Out of stock");
            addToCartButton.setEnabled(false);
        }
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
        this.getResources().flushLayoutCache();
        super.onDestroy();
    }

    // EventBus Subscribe
    public void onEvent(ProductByIdEvent getDatasEvent){
        try {
            if (getDatasEvent != null && getDatasEvent.getProduct() != null) {
                loadingFrame.setVisibility(View.GONE);
                product = getDatasEvent.getProduct();
                fillFields(getDatasEvent.getProduct());
            }
        }catch (Exception e){
            e.printStackTrace();
            loadingFrame.setVisibility(View.GONE);
            FrameLayout fl = (FrameLayout) findViewById(R.id.connectionErrorFrame);
            fl.setVisibility(View.VISIBLE);
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

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Override
    protected void onResume() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        super.onResume();
    }

    private void zoomImageFromThumb(final View thumbView, Product product) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        Picasso.with(this)
                .load(product.getProductGallery().getFeaturedImages())
                .fit()
                .centerInside()
                .placeholder(R.drawable.empty_photo)
                .error(R.drawable.default_product)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(expandedImageView);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }


}
