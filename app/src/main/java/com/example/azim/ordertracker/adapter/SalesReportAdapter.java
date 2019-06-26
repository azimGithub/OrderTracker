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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesReportAdapter extends RecyclerView.Adapter<SalesReportAdapter.ViewHolder> {
    private Context mContext;
    private List<Itmast> salesReport;

    public SalesReportAdapter(Context context, List<Itmast> salesReport){
        mContext = context;
        this.salesReport = salesReport;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //@BindView(R.id.iname)
        TextView iname;
        //@BindView(R.id.icode)
        TextView icode;
        //@BindView(R.id.qty)
        TextView qty;
        //@BindView(R.id.price)
        TextView price;
        //@BindView(R.id.outletCount)
        TextView outletCount;
        Itmast item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iname = itemView.findViewById(R.id.iname);
            icode = itemView.findViewById(R.id.icode);
            qty = itemView.findViewById(R.id.qty);
            price = itemView.findViewById(R.id.price);
            outletCount = itemView.findViewById(R.id.outletCount);
            //ButterKnife.bind(itemView);
        }

        public void setData(Itmast item) {
            this.item = item;
            if(item!=null){
                String itemName=  item.iname;
                if(itemName!=null && !itemName.isEmpty()){
                    iname.setText(itemName);
                }

                String itemCode=item.icode;
                if(itemCode!=null && !itemCode.isEmpty()){
                    icode.setText(itemCode);
                }

                String storeCount = item.outletCount;
                if (storeCount !=null && !storeCount.isEmpty()){
                    outletCount.setText(storeCount);
                }

                String itemPrice = item.price;
                if (itemPrice !=null && !itemPrice.isEmpty()){
                    price.setText(itemPrice);
                }

                String itemQty = item.quantity;
                if (itemQty!=null && !itemQty.isEmpty()){
                    qty.setText(itemQty);
                }

            }
        }
    }

    @NonNull
    @Override
    public SalesReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sales_achievement_report_row, viewGroup, false);
        return new SalesReportAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SalesReportAdapter.ViewHolder holder, int position) {

        holder.setData(salesReport.get(position));
    }

    @Override
    public int getItemCount() {
        return salesReport.size();
    }
}
