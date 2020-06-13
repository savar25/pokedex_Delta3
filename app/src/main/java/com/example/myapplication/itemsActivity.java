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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.model.item;
import com.example.model.itemSet;
import com.example.network.RetrofitClientInstance;
import com.example.network.getItemInstance;

import java.util.ArrayList;
import java.util.List;

public class itemsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    itemListAdapter listAdapter;
  List<item> itemList;
  ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        final EditText search=findViewById(R.id.itemSearch);
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
        progressBar=findViewById(R.id.itmpgb);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setClickable(false);


        getItemInstance itemInstance= RetrofitClientInstance.getRetrofit().create(getItemInstance.class);
        Call<itemSet> itemSetCall=itemInstance.getItems();
        itemSetCall.enqueue(new Callback<itemSet>() {
            @Override
            public void onResponse(Call<itemSet> call, Response<itemSet> response) {
                itemList=response.body().getItemList();
                generateList(itemList);
            }

            @Override
            public void onFailure(Call<itemSet> call, Throwable t) {
                Toast.makeText(itemsActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generateList(List<item> itemList1){
        progressBar.setVisibility(View.GONE);
       recyclerView=findViewById(R.id.item_rec);
        listAdapter=new itemListAdapter(itemList1);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(itemsActivity.this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider,null));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemsActivity.this));
        recyclerView.setAdapter(listAdapter);
    }

    public  void filter(String s){
        List<item> filterList=new ArrayList<>();
        for(item i:itemList){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        listAdapter.filterList(filterList);
    }
}
