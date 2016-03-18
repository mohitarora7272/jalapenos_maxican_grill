package com.serfcompany.ecommerce.acart.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
        drawerLayout = (DrawerLayout) findViewById(R.id.product_drawer_layout);

        productImage = (ImageView) findViewById(R.id.productActivityImage);
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(productImage, product);
            }
        });


        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        initToolbar();
        initNavigationView(drawerLayout, null);

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
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        Picasso.with(this)
                .load(product.getProductGallery().getFeaturedImages())
                .fit()
                .centerInside()
                .placeholder(R.drawable.empty_photo)
                .error(R.drawable.default_product)
                .into(expandedImageView);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
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
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
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

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
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
