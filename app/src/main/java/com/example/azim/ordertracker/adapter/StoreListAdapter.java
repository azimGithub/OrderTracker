package com.example.azim.ordertracker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.model.appData.Amast;
import com.example.azim.ordertracker.model.appData.Areama;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.view.CustomerAddress;
import com.example.azim.ordertracker.view.CustomerSelection;
import com.example.azim.ordertracker.view.authentication.areaSelection.AreaSearching;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ListViewHolder> implements Filterable {
    private List<Amast> storeLists;
    private List<Amast> storeListFiltered;
    private Context context;

    public StoreListAdapter(Context context, List<Amast> storeLists){
        this.storeLists = storeLists;
        this.context = context;
        this.storeListFiltered = storeLists;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customer_selection_item_row, viewGroup, false);
        return new ListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder( ListViewHolder listViewHolder, final int position) {
        final Amast store = storeListFiltered.get(position);
        listViewHolder.storeName.setText(store.name);
        listViewHolder.storeCode.setText(store.acode);
        listViewHolder.cartImage.setImageResource(store.img);
        listViewHolder.areaName.setText(store.add1);
        //listViewHolder.areaName.setText(((CustomerSelection)context).getArea());

        listViewHolder.customer_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Amast areama=      storeListFiltered.get(position);
                String areaCode=areama.areaCd;
                String aCode="";
                if(areama!=null){

                    ((CustomerSelection)context).setAddress(areama);

                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return storeListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                    storeListFiltered = storeLists;
                }else {
                    List<Amast> filteredList = new ArrayList<>();
                    for (Amast row : storeLists){
                        if (row.name.toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    storeListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = storeListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                storeListFiltered = (ArrayList<Amast>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ListViewHolder extends RecyclerView.ViewHolder  {
        //@BindView(R.id.storeName)
        TextView storeName,tvItemNo, areaName;

        //@BindView(R.id.item_cart)
        ImageView cartImage;

        //@BindView(R.id.dropImage)
        ImageView dropImage;

        //@BindView(R.id.storeCode)
        TextView storeCode;

        //@BindView(R.id.areaCode)
        TextView areaCode;

        LinearLayout customer_row;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            //ButterKnife.bind(this,itemView);
            storeName = itemView.findViewById(R.id.store_name);
            storeCode = itemView.findViewById(R.id.storeCode);
            cartImage = itemView.findViewById(R.id.item_cart);
            customer_row= itemView.findViewById(R.id.customer_row);
            areaName = itemView.findViewById(R.id.beatName);
        }


    }

    public void swapItems(List<Amast> items) {
        this.storeListFiltered = items;
        notifyDataSetChanged();
    }

}
