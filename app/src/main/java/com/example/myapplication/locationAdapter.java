package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.model.item;
import com.example.model.location;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class locationAdapter extends RecyclerView.Adapter<locationAdapter.locationHolder> {

    List<location> items=new ArrayList<>();

    public locationAdapter(List<location> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public  locationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        locationHolder IH=new locationHolder(view);
        return IH;
    }


    @Override
    public void onBindViewHolder(@NonNull locationHolder holder, int position) {
        holder.itemname.setText(changeLetter(items.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class locationHolder extends RecyclerView.ViewHolder{

        TextView itemname;
        public locationHolder(@NonNull View itemView) {
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

    public void filterList(List<location> locationList){
        items=locationList;
        notifyDataSetChanged();
    }
}
