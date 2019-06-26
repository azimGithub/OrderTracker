package com.example.azim.ordertracker.adapter;

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
import com.example.azim.ordertracker.model.appData.Areama;
import com.example.azim.ordertracker.view.CustomerSelection;
import com.example.azim.ordertracker.view.authentication.areaSelection.AreaSearching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AreaSearchAdapter extends RecyclerView.Adapter<AreaSearchAdapter.ListViewHolder> implements Filterable {
    private List<Areama> storeLists;
    private List<Areama> storeListFiltered;
    private Context context;

    public AreaSearchAdapter(Context context, List<Areama> storeLists){
        this.storeLists = storeLists;
        this.context = context;
        this.storeListFiltered = storeLists;
    }

    @NonNull
    @Override
    public AreaSearchAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.area_search_row, viewGroup, false);
        return new AreaSearchAdapter.ListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AreaSearchAdapter.ListViewHolder listViewHolder, final int i) {
        final Areama store = storeListFiltered.get(i);
        listViewHolder.storeName.setText(store.name);
        listViewHolder.storeCode.setText(store.areaCd);
        listViewHolder.cartImage.setImageResource(R.drawable.ic_shopping_cart_black_24dp);


        listViewHolder.customer_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Areama areama=      storeListFiltered.get(i);
                String areaCode="";
                String areaName="";
                if(areama!=null){

                     areaCode=   areama.areaCd;
                    areaName=   areama.name;
                }

                Intent intent = new Intent(context, CustomerSelection.class);
                intent.putExtra("EMAIL", "");
                intent.putExtra("areaCode",areaCode);
                intent.putExtra("areaName",areaName);
                intent.putExtra("areaPosition",i);

                context.  startActivity(intent);

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
                    List<Areama> filteredList = new ArrayList<>();
                    for (Areama row : storeLists){
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
                storeListFiltered = (ArrayList<Areama>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //@BindView(R.id.storeName)
        TextView storeName;

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
        }

        @Override
        public void onClick(View v) {



        }
    }
}
