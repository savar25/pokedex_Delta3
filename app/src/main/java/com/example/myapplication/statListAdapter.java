package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.model.outerStat;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class statListAdapter extends RecyclerView.Adapter<statListAdapter.statHolder>{


    List<outerStat> outerStatList=new ArrayList<>();
    Context context;

    public statListAdapter(List<outerStat> outerStatList, Context context) {
        this.outerStatList = outerStatList;
        this.context = context;
    }

    @NonNull
    @Override
    public statHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.stat_element,parent,false);
        statHolder VH=new statHolder(view);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull statHolder holder, int position) {
        holder.name.setText(changeLetter(outerStatList.get(position).getStat().getName()));
        holder.val.setText(String.valueOf(outerStatList.get(position).getStatVal()));
        if(position%2==0) {
            holder.constraintLayout.setBackgroundColor(holder.constraintLayout.getResources().getColor(R.color.green, null));
        }else {
            holder.constraintLayout.setBackgroundColor(holder.constraintLayout.getResources().getColor(R.color.randBlue, null));
        }
    }

    @Override
    public int getItemCount() {
        return outerStatList.size();
    }


    public class statHolder extends RecyclerView.ViewHolder{

        TextView name,val;
        ConstraintLayout constraintLayout;
        public statHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.statName);
            val=itemView.findViewById(R.id.statVal);
            constraintLayout=itemView.findViewById(R.id.statLayout);
        }
    }

    public String changeLetter(String s){
        String sub=s.substring(0,1);
        sub=sub.toUpperCase();
        s=sub+s.substring(1);
        return s;
    }
}
