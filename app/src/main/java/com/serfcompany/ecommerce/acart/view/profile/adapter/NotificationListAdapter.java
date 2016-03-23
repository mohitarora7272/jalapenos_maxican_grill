package com.serfcompany.ecommerce.acart.view.profile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.model.notification.NotificationData;
import com.serfcompany.ecommerce.acart.presenter.profile.MyNotificationFragmentPresenter;
import com.serfcompany.ecommerce.acart.view.OrderActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationsViewHolder> {

    List<NotificationData> notificationList;
    private Context context;
    private MyNotificationFragmentPresenter fragmentPresenter;


    public NotificationListAdapter(Context context, MyNotificationFragmentPresenter fragmentPresenter){
        this.context = context;
        notificationList = new ArrayList<>();
        this.fragmentPresenter = fragmentPresenter;
    }

    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {
        final NotificationData data = getItem(position);
        final int orderID = Integer.valueOf(data.getObjectID());
        holder.daysAgo.setText(data.getAgo());
        holder.message.setText(data.getPushedMessage());
        holder.date.setText(data.getDateTime());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, OrderActivity.class);
                intent.putExtra(Constants.ORDER_ID, String.valueOf(orderID));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public void setNotificationList(List<NotificationData> notificationList) {
        if (notificationList != null){
            this.notificationList.clear();
            this.notificationList.addAll(notificationList);
            notifyDataSetChanged();
        }
    }

    private NotificationData getItem(int position){
        return notificationList.get(position);
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView daysAgo;
        TextView message;
        TextView date;

        public NotificationsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.notificationCardView);
            daysAgo = (TextView) itemView.findViewById(R.id.notifDaysAgo);
            message = (TextView) itemView.findViewById(R.id.notificationMessage);
            date = (TextView) itemView.findViewById(R.id.notificationDate);
        }
    }
}
