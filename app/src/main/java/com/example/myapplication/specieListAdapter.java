package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.pokemon;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class specieListAdapter extends RecyclerView.Adapter<specieListAdapter.specieHolder> {

    List<pokemon> names=new ArrayList<>();
    Context context;

    public specieListAdapter(List<pokemon> names,Context context) {
        this.names = names;
        this.context=context;
    }

    @NonNull
    @Override
    public specieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.specie_list_element,parent,false);
        specieHolder viewHolder=new specieHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull specieHolder holder, int position) {
        holder.sName.setText(changeLetter(names.get(position).getName()));

        Picasso.Builder builder=new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(names.get(position).getSprites().getFront_default()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.circularImageView);

        Picasso.Builder builder1=new Picasso.Builder(context);
        builder1.downloader(new OkHttp3Downloader(context));
        builder1.build().load(names.get(position).getSprites().getFront_default()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.frontView);

        Picasso.Builder builder2=new Picasso.Builder(context);
        builder2.downloader(new OkHttp3Downloader(context));
        builder2.build().load(names.get(position).getSprites().getFront_shiny()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.sFrontView);

        Picasso.Builder builder4=new Picasso.Builder(context);
        builder4.downloader(new OkHttp3Downloader(context));
        builder4.build().load(names.get(position).getSprites().getBack_default()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.backView);

        Picasso.Builder builder5=new Picasso.Builder(context);
        builder5.downloader(new OkHttp3Downloader(context));
        builder5.build().load(names.get(position).getSprites().getBack_shiny()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.sBackView);
        ArrayList<String> nameList=new ArrayList<>();
        for(int i=0;i<names.get(position).getAbility().size();i++) {
            nameList.add(changeLetter(names.get(position).getAbility().get(i).getAbi().getAbiName()));
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_list_item_1,nameList);
        holder.abilityList.setAdapter(arrayAdapter);
        holder.constraintLayout.setVisibility(names.get(position).isExpandable()?View.VISIBLE:View.GONE);

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class specieHolder extends RecyclerView.ViewHolder{
        TextView sName;
        CircularImageView circularImageView,frontView,sFrontView,backView,sBackView;
        ListView abilityList;
        ConstraintLayout constraintLayout,outer;

        public specieHolder(@NonNull View itemView) {
            super(itemView);

            sName = itemView.findViewById(R.id.name1);
            circularImageView = itemView.findViewById(R.id.circularImageView1);
            abilityList = itemView.findViewById(R.id.abilityList1);
            frontView = itemView.findViewById(R.id.frontView1);
            sFrontView = itemView.findViewById(R.id.frontViewShiny1);
            backView = itemView.findViewById(R.id.backView1);
            sBackView = itemView.findViewById(R.id.backViewShiny1);
            constraintLayout = itemView.findViewById(R.id.expndable1);
            outer = itemView.findViewById(R.id.trial1);
            outer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokemon pokemon1 = names.get(getAdapterPosition());
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
}
