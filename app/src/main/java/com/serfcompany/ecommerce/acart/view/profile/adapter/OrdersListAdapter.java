package com.serfcompany.ecommerce.acart.view.profile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;
import com.serfcompany.ecommerce.acart.presenter.profile.MyOrdersFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.OrderActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.OrdersViewHolder>{

    private Context context;
    private List<MyOrder> orderList;
    private MyOrdersFragmentPresenter ordersFragmentPresenter;

    public OrdersListAdapter(Context context, MyOrdersFragmentPresenter ordersFragmentPresenter){
        this.context = context;
        this.orderList = new ArrayList<>();
        this.ordersFragmentPresenter = ordersFragmentPresenter;
    }

    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrdersViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        final MyOrder order = getItem(position);
        holder.orderID.setText("#"+String.valueOf(order.getOrderID()));
        holder.orderDate.setText(order.getOrderDate());
        holder.orderStatus.setText(order.getStatus());
        String currency = order.getCurrency();
        String orderTotal = ""+ currency +" "+String.valueOf(new DecimalFormat("0.00").format(order.getOrderTotal())) + "";
        holder.orderTotal.setText(Html.fromHtml(orderTotal));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, OrderActivity.class);
                intent.putExtra(Constants.ORDER_ID, String.valueOf(order.getOrderID()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public MyOrder getItem(int position){
        return orderList.get(position);
    }

    public void setDatas(List<MyOrder> orderList){
        if (orderList != null && orderList.size() > 0){
            this.orderList.clear();
            this.orderList.addAll(orderList);
            notifyDataSetChanged();
        }
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView orderID;
        TextView orderStatus;
        TextView orderTotal;

        TextView orderDate;
        public OrdersViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.orderCardView);
            orderID = (TextView) itemView.findViewById(R.id.orderID);
            orderStatus = (TextView) itemView.findViewById(R.id.orderStatus);
            orderTotal = (TextView) itemView.findViewById(R.id.orderTotal);
            orderDate = (TextView) itemView.findViewById(R.id.orderDate);
        }

    }
}
