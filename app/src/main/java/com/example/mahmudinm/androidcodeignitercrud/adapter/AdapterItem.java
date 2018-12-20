package com.example.mahmudinm.androidcodeignitercrud.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmudinm.androidcodeignitercrud.R;
import com.example.mahmudinm.androidcodeignitercrud.model.Item;

import java.util.List;

/**
 * Created by Mahmudinm on 20/12/2018.
 */

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {

    private List<Item> list;

    public AdapterItem(List<Item> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,
                parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item dataItem = list.get(position);
        holder.id.setText(dataItem.getId());
        holder.nama.setText(dataItem.getNama());
        holder.harga.setText(dataItem.getHarga());
        holder.dataItem = dataItem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, nama, harga;
        Item dataItem;

        public ViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.tvId);
            nama = (TextView) itemView.findViewById(R.id.tvNama);
            harga = (TextView) itemView.findViewById(R.id.tvHarga);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
