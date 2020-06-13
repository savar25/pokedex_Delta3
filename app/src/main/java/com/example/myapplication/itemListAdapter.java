package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.model.item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class itemListAdapter extends RecyclerView.Adapter<itemListAdapter.itemHolder> {

    List<item> items=new ArrayList<>();

    public itemListAdapter(List<item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        itemHolder IH=new itemHolder(view);
        return IH;
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {
        holder.itemname.setText(changeLetter(items.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder{

        TextView itemname;
        public itemHolder(@NonNull View itemView) {
            super(itemView);

            itemname=itemView.findViewById(R.id.item_name);
        }
    }


    public String changeLetter(String s) {
        String sub = s.substring(0, 1);
        sub = sub.toUpperCase();
        s = sub + s.substring(1);
        return s;
    }

    public void filterList(List<item> items1){
        items=items1;
        notifyDataSetChanged();
    }
}
