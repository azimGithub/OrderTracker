package com.example.azim.ordertracker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.view.OrderSummary;

import java.util.ArrayList;
import java.util.List;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    Context mContext;
    List<Itmast> orderSummaryList;


    public OrderSummaryAdapter(Context context, List<Itmast> values){

        mContext = context;
        orderSummaryList = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvItemNo,tvItemName,total_price_tv, tvItemQty;
        Itmast item;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemNo = itemView.findViewById(R.id.itemNo);
            tvItemName = itemView.findViewById(R.id.itemName);
            total_price_tv = itemView.findViewById(R.id.totalPrice);
            tvItemQty = itemView.findViewById(R.id.itemQty);
        }

        public void setData(Itmast item, final int position) {
            this.item = item;
            if(item!=null){
                String itemName=  item.iname;
                if(itemName!=null && !itemName.isEmpty()){
                    tvItemName.setText(itemName);
                }
                String itemNo=item.icode;
                if(itemNo!=null && !itemNo.isEmpty()){
                    tvItemNo.setText(itemNo);
                }

                String totalPrice=item.price;
                if(totalPrice!=null && !totalPrice.isEmpty()){
                    total_price_tv.setText(totalPrice);
                }

                String itemQty = item.quantity;
                if (itemQty != null && !itemQty.isEmpty()){
                   tvItemQty.setText(itemQty);
                }

            }
        }

    }

    @NonNull
    @Override
    public OrderSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_order_summary_row, viewGroup, false);
        return new OrderSummaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderSummaryAdapter.ViewHolder viewHolder, int position) {

        viewHolder.setData(orderSummaryList.get(position), position);
        ((OrderSummary)mContext).setValue(orderSummaryList);

        //viewHolder.tvItemNo.setText(orderSummaryList.get(position).toString());
        //viewHolder.tvItemName.setText(orderSummaryList.get(position).toString());
        //viewHolder.total_price_tv.setText(orderSummaryList.get(position).toString());
        //viewHolder.tvItemQty.setText(orderSummaryList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return orderSummaryList.size();
    }

}
