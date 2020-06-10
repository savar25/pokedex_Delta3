package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.pokemon;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder> {




    private static final String TAG = "rvAdapter";
    List<pokemon> pokemonList=new ArrayList<>();
    Context context;

    public rvAdapter(List<pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.element_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: "+ position);
        holder.name.setText(changeLetter(pokemonList.get(position).getName()));

        Picasso.Builder builder=new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(pokemonList.get(position).getSprites().getFront_default()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.circularImageView);

        Picasso.Builder builder1=new Picasso.Builder(context);
        builder1.downloader(new OkHttp3Downloader(context));
        builder1.build().load(pokemonList.get(position).getSprites().getFront_default()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.frontView);

        Picasso.Builder builder2=new Picasso.Builder(context);
        builder2.downloader(new OkHttp3Downloader(context));
        builder2.build().load(pokemonList.get(position).getSprites().getFront_shiny()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.sFrontView);

        Picasso.Builder builder4=new Picasso.Builder(context);
        builder4.downloader(new OkHttp3Downloader(context));
        builder4.build().load(pokemonList.get(position).getSprites().getBack_default()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.backView);

        Picasso.Builder builder5=new Picasso.Builder(context);
        builder5.downloader(new OkHttp3Downloader(context));
        builder5.build().load(pokemonList.get(position).getSprites().getBack_shiny()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.sBackView);

        ArrayList<String> names=new ArrayList<>();
        for(int i=0;i<pokemonList.get(position).getAbility().size();i++) {
            names.add(changeLetter(pokemonList.get(position).getAbility().get(i).getAbi().getAbiName()));
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_list_item_1,names);
        holder.abilityList.setAdapter(arrayAdapter);
        Log.d(TAG, "onBindViewHolder: "+pokemonList.get(position).isExpandable());
        holder.constraintLayout.setVisibility(pokemonList.get(position).isExpandable()?View.VISIBLE:View.GONE);


    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
            TextView name;
            CircularImageView circularImageView,frontView,sFrontView,backView,sBackView;
            ListView abilityList;
            ConstraintLayout constraintLayout,outer;
            CardView cardView;
            Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            circularImageView=itemView.findViewById(R.id.circularImageView);
            abilityList=itemView.findViewById(R.id.abilityList);
            frontView=itemView.findViewById(R.id.frontView);
            sFrontView=itemView.findViewById(R.id.frontViewShiny);
            backView=itemView.findViewById(R.id.backView);
            sBackView=itemView.findViewById(R.id.backViewShiny);
            constraintLayout=itemView.findViewById(R.id.expndable);
            cardView=itemView.findViewById(R.id.lel);
            outer=itemView.findViewById(R.id.trial);
            button=itemView.findViewById(R.id.detBtn);
            outer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokemon pokemon1=pokemonList.get(getAdapterPosition());
                    pokemon1.setExpandable(!(pokemon1.isExpandable()));
                    notifyItemChanged(getAdapterPosition());

                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokemon pokemon2=pokemonList.get(getAdapterPosition());
                    Intent intent=new Intent(context,pokeDetails.class);
                    intent.putExtra("pokemon",pokemon2);
                    context.startActivity(intent);
                }
            });

        }
    }
    public String changeLetter(String s){
        String sub=s.substring(0,1);
        sub=sub.toUpperCase();
        s=sub+s.substring(1);
        Log.d(TAG, "changeLetter: "+s);
        return s;
    }

    public void filterList(List<pokemon> pokemonList1){
        pokemonList=pokemonList1;
        notifyDataSetChanged();
    }

}
