package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.pokedex;
import com.example.model.pokemon;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class regionAdapter extends RecyclerView.Adapter<regionAdapter.myViewHolder> {

    List<List<pokemon>> SpecieName=new ArrayList<>();
    List<pokedex> pokedexList=new ArrayList<>();
    Context context;

    public regionAdapter(List<List<pokemon>> specieName, List<pokedex> pokedexList, Context context) {
        SpecieName = specieName;
        this.pokedexList = pokedexList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.region_list_element,parent,false);
        myViewHolder viewHolder=new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.name.setText(changeLetter(pokedexList.get(position).getName()));
        specieListAdapter specieListAdapter=new specieListAdapter(SpecieName.get(position),context);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(specieListAdapter);

        holder.inner.setVisibility(pokedexList.get(position).isExpandable()?View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return pokedexList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout inner,outer;
        TextView name;
        RecyclerView recyclerView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.regionName);
            recyclerView=itemView.findViewById(R.id.rec2);
            inner=itemView.findViewById(R.id.inner);
            outer=itemView.findViewById(R.id.clayout);


            outer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokedex pokedex1=pokedexList.get(getAdapterPosition());
                    pokedex1.setExpandable(!(pokedex1.isExpandable()));
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

    public void filterList(List<pokedex> pokemonList1){
        pokedexList=pokemonList1;
        notifyDataSetChanged();
    }
}
