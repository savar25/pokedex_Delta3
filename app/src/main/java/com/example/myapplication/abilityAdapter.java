package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class abilityAdapter extends RecyclerView.Adapter<abilityAdapter.abilityViewHolder>{

    List<String> abiltyList=new ArrayList<>();

    public abilityAdapter(List<String> abiltyList) {
        this.abiltyList = abiltyList;
    }

    @NonNull
    @Override
    public abilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ability_list_layout,parent,false);
        abilityViewHolder abilityViewHolder=new abilityViewHolder(view);
        return abilityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull abilityViewHolder holder, int position) {
            holder.textView.setText(abiltyList.get(position));
    }

    @Override
    public int getItemCount() {
        return abiltyList.size();
    }

    public class abilityViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public abilityViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.abName);
        }
    }
}
