package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button poke=findViewById(R.id.pokemon);
        Button regio=findViewById(R.id.regions);
        Button fav=findViewById(R.id.favListBtn);
        Button items=findViewById(R.id.imgbtn);
        Button locations=findViewById(R.id.locationsbtn);

        poke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Choice.this,list.class);
                startActivity(intent);
            }
        });

        regio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Choice.this,regionList.class);
                startActivity(intent);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Choice.this,favouriteList.class);
                startActivity(intent);
            }
        });

        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Choice.this,itemsActivity.class);
                startActivity(intent);
            }
        });

        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Choice.this,locationActivity.class);
                startActivity(intent);
            }
        });



    }
}
