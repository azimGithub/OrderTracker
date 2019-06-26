package com.example.azim.ordertracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.model.appData.Amast;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.utlility.DecimalUtils;
import com.example.azim.ordertracker.view.ItemMasterList;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> implements Filterable {
    public static final int ITEM = 0;
    public static final int NO_ITEM = 1;

    List<Itmast> itemList;
    List<Itmast> brandItems;
    List<Itmast> selectedList;
    Context mContext;
    private String itemCount;
    private List<Itmast> storeListFiltered;

    public ItemListAdapter(final Context context,final List<Itmast> values) {

        itemList = values;
        mContext = context;
        storeListFiltered=values;

    }

    public ItemListAdapter(List<Itmast> list){
        selectedList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public RelativeLayout relativeLayout;
        public EditText etItems;
        public ImageView ivPlus, ivMinus,circleImg;
        public TextView tvUnitRate, tvItemNo, tvItemName, total_price_tv;
        Itmast item;

        public ViewHolder(View v) {

            super(v);

            //  v.setOnClickListener(this);
            tvItemName = (TextView) v.findViewById(R.id.itemName);
            tvUnitRate = (TextView) v.findViewById(R.id.tvUnitRate);
            tvItemNo = (TextView) v.findViewById(R.id.tvItemNo);
            etItems = (EditText) v.findViewById(R.id.itemQuantity);
            ivPlus = (ImageView) v.findViewById(R.id.ivPlusView);
            ivMinus = (ImageView) v.findViewById(R.id.ivMinus);
            total_price_tv = (TextView) v.findViewById(R.id.inr_tv);
            circleImg = v.findViewById(R.id.circleImg);
            etItems.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }


                @Override
                public void onTextChanged(CharSequence charSequence, int start,
                                          int before, int count) {
                    //if (charSequence.length() != 0 && charSequence.length() > 0) {

                        Itmast itmast = itemList.get(getAdapterPosition());
                        if (itmast != null) {

                            String saleRate = itmast.saleRate;
                            String gstRate = itmast.gstPer;
                            int factor = itmast.factor;

                            if (!charSequence.toString().isEmpty()) {
                                double i = Double.parseDouble(charSequence.toString());
                                if (i > 0.0) {
                                    circleImg.setImageResource(R.drawable.ic_check_circle_black_24dp);
                                } else {
                                    circleImg.setImageResource(R.drawable.ic_blur_circular_black_24dp);
                                }
                            }else {
                                circleImg.setImageResource(R.drawable.ic_blur_circular_black_24dp);
                            }

                                double dSaleRate = Double.parseDouble(saleRate);
                                double dGstRate = Double.parseDouble(gstRate);

                                double intQun;
                                if (charSequence.toString().isEmpty()){
                                      intQun = 0.0;

                                }else {
                                     intQun = Double.parseDouble(charSequence.toString());
                                }
                                Double priceValue = null;
                                if (dSaleRate > 0.0) {

                                    double dSaleVaue = dSaleRate * intQun * factor;
                                    double dGstValue = dGstRate * intQun;

                                    priceValue = dSaleVaue; //+ dGstValue;
                                    priceValue = DecimalUtils.round(priceValue, 2);

                                }
                                String priceCal = "";
                                if (priceValue != null) {
                                    priceCal = String.valueOf(priceValue);

                                }
                                if (priceValue!=null){
                                total_price_tv.setText("" + priceValue.toString() + " INR");}
                                itmast.quantity = charSequence.toString();
                                itmast.price = priceCal.toString();

                                itemList.set(getAdapterPosition(), itmast);
                                    ((ItemMasterList) mContext).setValue(itmast, getAdapterPosition(), charSequence.toString());

                                //  notifyDataSetChanged();
                            }
                    //}
                }
            });


        }
    }

        public void setData(final Itmast item, final int position, final ViewHolder viewHolder) {
            if (item != null) {
                String itemName = item.iname;
                if (itemName != null && !itemName.isEmpty()) {
                    viewHolder.tvItemName.setText(itemName);
                }
                String unitRate = item.saleRate;
                double rate = Double.parseDouble(unitRate);
                rate = DecimalUtils.round(rate,2);

                if (unitRate != null && !unitRate.isEmpty()) {
                    viewHolder.tvUnitRate.setText("@" + rate);
                }

                String itemCode = item.icode;
                if (itemCode != null && !itemCode.isEmpty()) {
                    viewHolder.tvItemNo.setText("("+itemCode+")");
                }

                viewHolder.circleImg.setImageResource(R.drawable.ic_blur_circular_black_24dp);

            }
        }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_master_list, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder Vholder,final int position) {

            setData(itemList.get(Vholder.getAdapterPosition()),Vholder.getAdapterPosition(),Vholder);
            String quantity= itemList.get(Vholder.getAdapterPosition()).quantity;
            if(quantity!=null && !quantity.isEmpty()){
                Vholder.etItems.setText(quantity);
            }else{
                Vholder.etItems.setText(null);
            }

            if (quantity!=null && !quantity.isEmpty()){
                if (Double.valueOf(quantity)>0) {
                    Vholder.circleImg.setImageResource(R.drawable.ic_check_circle_black_24dp);
                }
            }else {
                Vholder.circleImg.setImageResource(R.drawable.ic_blur_circular_black_24dp);
            }

            String total_Value= itemList.get(Vholder.getAdapterPosition()).price;
            if(total_Value!=null && !total_Value.isEmpty()){
                Vholder.total_price_tv.setText(total_Value);
            }else{
                Vholder.total_price_tv.setText(null);
            }

            Vholder. ivPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemCount=   Vholder. etItems.getText().toString();
                    if(itemCount!=null && !itemCount.isEmpty()){
                        double itemCountInt = Double.parseDouble(itemCount);
                        itemCountInt=itemCountInt+1;
                        itemCount= Double.toString(itemCountInt);
                    }else{
                        double itemCountInt=0;
                        itemCountInt=itemCountInt+1;
                        itemCount= Double.toString(itemCountInt);
                    }
                    Vholder. etItems.setText(itemCount);
                }
            });

            Vholder.  ivMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    itemCount=   Vholder. etItems.getText().toString();

                    if(itemCount!=null && !itemCount.isEmpty()){

                        double itemCountInt = Double.parseDouble(itemCount);

                        if(itemCountInt>0.0){

                            itemCountInt=itemCountInt-1;
                            itemCount= Double.toString(itemCountInt);
                        }else {
                            itemCount = "";
                        }

                    }else{

                    }

                    Vholder. etItems.setText(itemCount);

                }
            });
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                    itemList = storeListFiltered;
                }else {
                    List<Itmast> filteredList = new ArrayList<>();
                    for (Itmast row : storeListFiltered){
                        if (row.iname.toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    itemList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = itemList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemList = (ArrayList<Itmast>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void swapItems(List<Itmast> items) {
        this.itemList = items;
        notifyDataSetChanged();
    }

    public void getSelectedItem(){
        selectedList = new ArrayList<>();
        for (Itmast row : itemList){
            String quan = row.quantity;
            if (!TextUtils.isEmpty(quan)) {
                double qty = Double.valueOf(quan);
                if (qty > 0.0) {
                    selectedList.add(row);
                }
            }
        }
        Log.d(TAG, "size:" + selectedList.size());
        swapItems(selectedList);
        ((ItemMasterList) mContext).setVisibility1(selectedList);
    }

}