package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.pokemon;
import com.example.network.RetrofitClientInstance;
import com.example.network.getDataInstance;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {
    ProgressBar progressDialog;
    public List<pokemon> nameList=new ArrayList<>();
    private static final String TAG = "MainActivity";
    Integer btnVal=0;
    int k=25;
    rvAdapter rvAdapter;
    RecyclerView recyclerView;
   sqlDatabase database;
   List<pokemon> filter1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d(TAG, "onCreate: ");

        database=new sqlDatabase(this);
        progressDialog=findViewById(R.id.progressBar);
        Drawable drawable=getDrawable(R.drawable.circular);
        progressDialog.setProgressDrawable(drawable);
        progressDialog.setVisibility(View.VISIBLE);
        progressDialog.setClickable(false);

        final EditText search=findViewById(R.id.searchBar);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    search.setHint("");
                }else{
                    search.setHint("Search");
                }
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });



        setPager();
        for (int i = 1; i <=25 ; i+=2) {
            getDataInstance instance = RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
            getDataInstance instance1 = RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
            Call<pokemon> pokeDetils = instance.getInfo(i);
            pokeDetils.enqueue(new Callback<pokemon>() {
                @Override
                public void onResponse(Call<pokemon> call, Response<pokemon> response) {

                    for (int i = 0; i < response.body().getAbility().size(); i++) {
                        Log.d(TAG, "onResponse: abilities" + response.body().getAbility().get(i).getAbi().getAbiName());
                    }
                    nameList.add(response.body());
                    Log.d(TAG, "onResponse: " + nameList.size());
                    generateList(nameList);

                }

                @Override
                public void onFailure(Call<pokemon> call, Throwable t) {
                    Toast.makeText(list.this, "System Failure", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: "+j);
                    j++;
                    k--;
                }
            });

            Call<pokemon> pokemonCall = instance1.getInfo(i + 1);
            pokemonCall.enqueue(new Callback<pokemon>() {
                @Override
                public void onResponse(Call<pokemon> call, Response<pokemon> response) {
                    for (int i = 0; i < response.body().getAbility().size(); i++) {
                        Log.d(TAG, "onResponse: abilities" + response.body().getAbility().get(i).getAbi().getAbiName());
                    }
                    nameList.add(response.body());
                    Log.d(TAG, "onResponse: " + nameList.size());
                    generateList(nameList);
                }

                @Override
                public void onFailure(Call<pokemon> call, Throwable t) {
                    Toast.makeText(list.this, "System Failure", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: "+j);
                    j++;
                    k--;
                }
            });
        }


            ImageView front=findViewById(R.id.frontBtn);
            ImageView back=findViewById(R.id.backButton);

            front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    search.setText("");
                    search.setHint("Search");
                    k=25;
                    progressDialog.setVisibility(View.VISIBLE);
                    nameList.clear();
                    btnVal++;
                    setPager();
                    for (int i = 25*btnVal+1; i <=25*btnVal+25 ; i+=2) {
                        getDataInstance instance = RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
                        getDataInstance instance1 = RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
                        Call<pokemon> pokeDetils = instance.getInfo(i);
                        pokeDetils.enqueue(new Callback<pokemon>() {
                            @Override
                            public void onResponse(Call<pokemon> call, Response<pokemon> response) {

                                for (int i = 0; i < response.body().getAbility().size(); i++) {
                                    Log.d(TAG, "onResponse: abilities" + response.body().getAbility().get(i).getAbi().getAbiName());
                                }
                                nameList.add(response.body());
                                Log.d(TAG, "onResponse: " + nameList.size());
                                generateList(nameList);

                            }

                            @Override
                            public void onFailure(Call<pokemon> call, Throwable t) {
                                Toast.makeText(list.this, "System Failure", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onFailure: "+j);
                                j++;
                                k--;
                            }
                        });

                        Call<pokemon> pokemonCall = instance1.getInfo(i + 1);
                        pokemonCall.enqueue(new Callback<pokemon>() {
                            @Override
                            public void onResponse(Call<pokemon> call, Response<pokemon> response) {
                                for (int i = 0; i < response.body().getAbility().size(); i++) {
                                    Log.d(TAG, "onResponse: abilities" + response.body().getAbility().get(i).getAbi().getAbiName());
                                }
                                nameList.add(response.body());
                                Log.d(TAG, "onResponse: " + nameList.size());
                                generateList(nameList);
                            }

                            @Override
                            public void onFailure(Call<pokemon> call, Throwable t) {
                                Toast.makeText(list.this, "System Failure", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onFailure: "+j);
                                j++;
                                k--;
                            }
                        });
                    }

                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    search.setText("");
                    search.setHint("Search");
                    if (btnVal == 0) {
                        Toast.makeText(list.this, "No Previous Page", Toast.LENGTH_SHORT).show();
                    } else {
                        setPager();
                        k = 25;
                        progressDialog.setVisibility(View.VISIBLE);
                        nameList.clear();
                        btnVal--;
                        setPager();
                        for (int i = 25 * btnVal + 1; i <= 25 * btnVal + 25; i += 2) {
                            getDataInstance instance = RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
                            getDataInstance instance1 = RetrofitClientInstance.getRetrofit().create(getDataInstance.class);

                            Call<pokemon> pokeDetils = instance.getInfo(i);
                            pokeDetils.enqueue(new Callback<pokemon>() {
                                @Override
                                public void onResponse(Call<pokemon> call, Response<pokemon> response) {

                                    for (int i = 0; i < response.body().getAbility().size(); i++) {
                                        Log.d(TAG, "onResponse: abilities" + response.body().getAbility().get(i).getAbi().getAbiName());
                                    }
                                    nameList.add(response.body());
                                    Log.d(TAG, "onResponse: " + nameList.size());
                                    generateList(nameList);

                                }

                                @Override
                                public void onFailure(Call<pokemon> call, Throwable t) {
                                    Toast.makeText(list.this, "System Failure", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onFailure: " + j);
                                    j++;
                                    k--;
                                }
                            });

                            Call<pokemon> pokemonCall = instance1.getInfo(i + 1);
                            pokemonCall.enqueue(new Callback<pokemon>() {
                                @Override
                                public void onResponse(Call<pokemon> call, Response<pokemon> response) {
                                    for (int i = 0; i < response.body().getAbility().size(); i++) {
                                        Log.d(TAG, "onResponse: abilities" + response.body().getAbility().get(i).getAbi().getAbiName());
                                    }
                                    nameList.add(response.body());
                                    Log.d(TAG, "onResponse: " + nameList.size());
                                    generateList(nameList);
                                }

                                @Override
                                public void onFailure(Call<pokemon> call, Throwable t) {
                                    Toast.makeText(list.this, "System Failure", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onFailure: " + j);
                                    j++;
                                    k--;
                                }
                            });
                        }

                    }
                }
            });
        }

            public int j=1;

    public void filter(String s){
        List<pokemon> filterList=new ArrayList<>();
        for(pokemon p:nameList){
            if(p.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(p);
            }
        }

        filter1=filterList;
        rvAdapter.filterList(filter1);

    }

    public void generateList(List<pokemon> nameList){
            if(this.nameList.size()==k) {
                filter1=nameList;
                progressDialog.setVisibility(View.GONE);
                recyclerView=findViewById(R.id.rec);
                rvAdapter=new rvAdapter(nameList,list.this);
                Log.d(TAG, "generateList: "+nameList);
                new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(list.this));
                recyclerView.setAdapter(rvAdapter);
            }

    }


    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }



        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            if (filter1 == nameList) {
                pokemon itemPoke = nameList.get(viewHolder.getAdapterPosition());

                if (checkTable(itemPoke.getName())) {
                    Toast.makeText(list.this, "Already in Favourites", Toast.LENGTH_SHORT).show();
                    rvAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(list.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                    database.addPoke(itemPoke);
                    nameList.remove(viewHolder.getAdapterPosition());

                    rvAdapter.notifyDataSetChanged();
                }
            }else{

                pokemon itemPoke = filter1.get(viewHolder.getAdapterPosition());

                if (checkTable(itemPoke.getName())) {
                    Toast.makeText(list.this, "Already in Favourites", Toast.LENGTH_SHORT).show();
                    rvAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(list.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                    database.addPoke(itemPoke);
                    filter1.remove(viewHolder.getAdapterPosition());
                    nameList.remove(itemPoke);
                    rvAdapter.notifyDataSetChanged();
                }

            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            boolean flag = false;
            pokemon itemPoke = nameList.get(viewHolder.getAdapterPosition());
            if(checkTable(itemPoke.getName())){
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(list.this, R.color.red))
                        .addActionIcon(R.drawable.ic_not)
                        .addSwipeRightLabel("Already In Favourites")
                        .setSwipeRightLabelColor(getResources().getColor(R.color.white,null))
                        .setSwipeRightLabelTextSize((int)TypedValue.COMPLEX_UNIT_DIP,14)
                        .create()
                        .decorate();
            }else {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(list.this, R.color.red))
                        .addActionIcon(R.drawable.ic_fav_whilte)
                        .addSwipeRightLabel("Add To Favourites")
                        .setSwipeRightLabelColor(getResources().getColor(R.color.white, null))
                        .setSwipeRightLabelTextSize((int) TypedValue.COMPLEX_UNIT_DIP, 14)
                        .create()
                        .decorate();
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


        }
    };


    public void setPager(){
        TextView pager=findViewById(R.id.pager);
        pager.setText("Page: "+ (btnVal+1)+"/25");
    }

    public boolean checkTable(String s){
        Cursor cursor=database.getNames();
        while(cursor.moveToNext()){
            if(s.toLowerCase().equals(cursor.getString(0).toLowerCase())){
                return true;
            }
        }
        return false;
    }
}

