package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.model.item;
import com.example.model.itemSet;
import com.example.model.location;
import com.example.model.locationSet;
import com.example.network.RetrofitClientInstance;
import com.example.network.getItemInstance;
import com.example.network.getLocationInstance;

import java.util.ArrayList;
import java.util.List;

public class locationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    locationAdapter listAdapter;
    List<location> itemList;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        progressBar=findViewById(R.id.loc_pgb);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setClickable(false);

        final EditText search=findViewById(R.id.location_search);
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



        itemList=new ArrayList<>();

            getLocationInstance itemInstance= RetrofitClientInstance.getRetrofit().create(getLocationInstance.class);
            Call<locationSet> itemSetCall=itemInstance.getLocations();
            itemSetCall.enqueue(new Callback<locationSet>() {
                @Override
                public void onResponse(Call<locationSet> call, Response<locationSet> response) {
                    itemList=response.body().getLocationList();
                    generateList(itemList);
                }

                @Override
                public void onFailure(Call<locationSet> call, Throwable t) {
                    Toast.makeText(locationActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void generateList(List<location> itemList1){
        progressBar.setVisibility(View.GONE);
             recyclerView=findViewById(R.id.loc_rec);
             listAdapter=new locationAdapter(itemList1);
            DividerItemDecoration itemDecoration=new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider,null));
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setLayoutManager(new LinearLayoutManager(locationActivity.this));
            recyclerView.setAdapter(listAdapter);
        }

        public void filter(String s){
        List<location> filterList=new ArrayList<>();
        for(location l:itemList){
            if(l.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(l);
            }
        }
        listAdapter.filterList(filterList);
        }
    }

