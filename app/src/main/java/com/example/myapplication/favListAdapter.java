package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.model.favListItem;
import com.example.model.pokemon;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class favListAdapter extends RecyclerView.Adapter<favListAdapter.favHolder>{


    List<favListItem> favListItems=new ArrayList<>();
    Context context;

    public favListAdapter(List<favListItem> favListItems, Context context) {
        this.favListItems = favListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public favHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.fav_list_element,parent,false);
        favHolder vh=new favHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull favHolder holder, int position) {

        holder.favName.setText(changeLetter(favListItems.get(position).getName()));

        Picasso.Builder builder5=new Picasso.Builder(context);
        builder5.downloader(new OkHttp3Downloader(context));
        builder5.build().load(favListItems.get(position).getImgUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.favPic);

        Picasso.Builder builder4=new Picasso.Builder(context);
        builder4.downloader(new OkHttp3Downloader(context));
        builder4.build().load(favListItems.get(position).getFimg()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.fimg);

        Picasso.Builder builder3=new Picasso.Builder(context);
        builder3.downloader(new OkHttp3Downloader(context));
        builder3.build().load(favListItems.get(position).getFsimg()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.fsimg);

        Picasso.Builder builder2=new Picasso.Builder(context);
        builder2.downloader(new OkHttp3Downloader(context));
        builder2.build().load(favListItems.get(position).getBimg()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.bimg);

        Picasso.Builder builder1=new Picasso.Builder(context);
        builder1.downloader(new OkHttp3Downloader(context));
        builder1.build().load(favListItems.get(position).getBsimg()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.bsimg);

        List<String> names = new ArrayList<>();
        for (int i = 0; i < favListItems.get(position).getAbilityList().size(); i++) {
            names.add(changeLetter(favListItems.get(position).getAbilityList().get(i).getAbi().getAbiName()));
        }

        abilityAdapter arrayAdapter1=new abilityAdapter(names);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(context.getDrawable(R.drawable.divider));
        holder.recyclerView.addItemDecoration(itemDecoration);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(arrayAdapter1);

        holder.inner.setVisibility(favListItems.get(position).isExpandable()?View.VISIBLE:View.GONE);

    }

    @Override
    public int getItemCount() {
        return favListItems.size();
    }

    public class favHolder extends RecyclerView.ViewHolder{
        CircularImageView favPic,fimg,fsimg,bimg,bsimg;
        TextView favName;
        RecyclerView recyclerView;
        ConstraintLayout outer,inner;


        public favHolder(@NonNull View itemView) {
            super(itemView);

            favPic=itemView.findViewById(R.id.fav_pic);
            favName=itemView.findViewById(R.id.fav_name);
            fimg=itemView.findViewById(R.id.frontView2);
            fsimg=itemView.findViewById(R.id.frontViewShiny2);
            bimg=itemView.findViewById(R.id.backView2);
            bsimg=itemView.findViewById(R.id.backViewShiny2);
            recyclerView=itemView.findViewById(R.id.abilityList2);
            outer=itemView.findViewById(R.id.favOuter);
            inner=itemView.findViewById(R.id.expandable2);

            outer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favListItem pokemon1 = favListItems.get(getAdapterPosition());
                    pokemon1.setExpandable(!(pokemon1.isExpandable()));
                    notifyItemChanged(getAdapterPosition());

                }
            });


        }
    }

    public String changeLetter(String s){
        String sub=s.substring(0,1);
        sub=sub.toUpperCase();
        s=sub+s.substring(1);
        return s;
    }


    public void filterList(List<favListItem> listItems){
        favListItems=listItems;
        notifyDataSetChanged();
    }
}
