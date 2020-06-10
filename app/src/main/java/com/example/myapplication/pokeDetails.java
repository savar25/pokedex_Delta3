package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.outerStat;
import com.example.model.pokemon;
import com.example.model.stat;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class pokeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_details);

        ImageView pic = findViewById(R.id.pic);
        TextView name = findViewById(R.id.nameM);
        TextView type = findViewById(R.id.type);
        List<String> stats=new ArrayList<>();
        TextView weight=findViewById(R.id.weight);
        TextView height=findViewById(R.id.height);
        TextView base=findViewById(R.id.base);

        Intent intent=getIntent();
        pokemon pokemon1=(pokemon)intent.getSerializableExtra("pokemon");
        setTitle(pokemon1.getName());

        type.setText(changeLetter(pokemon1.getOuterTypeList().get(0).getType().getName()));
        name.setText(changeLetter(pokemon1.getName()));
        Picasso.Builder builder5=new Picasso.Builder(pokeDetails.this);
        builder5.downloader(new OkHttp3Downloader(pokeDetails.this));
        builder5.build().load(pokemon1.getSprites().getFront_default()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(pic);

        for(outerStat statobj:pokemon1.getOuterStatList()){
            stats.add(changeLetter(statobj.getStat().getName()));
        }



        RecyclerView recyclerView=findViewById(R.id.statRec);
        statListAdapter adapter=new statListAdapter(pokemon1.getOuterStatList(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        weight.setText(String.valueOf(pokemon1.getWeight()));
        height.setText(String.valueOf(pokemon1.getHeight()));
        base.setText(String.valueOf(pokemon1.getBaseExp()));

    }

    public String changeLetter(String s){
        String sub=s.substring(0,1);
        sub=sub.toUpperCase();
        s=sub+s.substring(1);
        return s;
    }

    public void generateStatList(){

    }
}
