package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.abilities;
import com.example.model.favListItem;
import com.example.model.outerAbility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class favouriteList extends AppCompatActivity {
    private static final String TAG = "favouriteList";

    sqlDatabase Database;
    List<favListItem> favListItems;
    favListAdapter favListAdapter;
    RecyclerView fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        Database=new sqlDatabase(this);
        favListItems=new ArrayList<>();

        final EditText search=findViewById(R.id.fav_search);
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


        Cursor cursor=Database.getPoke();
        while(cursor.moveToNext()){

            Gson gson=new Gson();
            Type type = new TypeToken<List<outerAbility>>() {}.getType();
            List<outerAbility> abilityList = gson.fromJson(cursor.getString(6), type);
            favListItems.add(new favListItem(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),abilityList));
            Log.d(TAG, "onCreate: "+cursor.getString(0)+","+ cursor.getString(1));
        }

        if(favListItems.size()!=0) {
            fav = findViewById(R.id.fav_rec);
            favListAdapter = new favListAdapter(favListItems, favouriteList.this);
            new ItemTouchHelper(simpleCallback).attachToRecyclerView(fav);
            fav.setLayoutManager(new LinearLayoutManager(favouriteList.this));

            fav.setAdapter(favListAdapter);
        }else{
            Toast.makeText(this,"No favourites",Toast.LENGTH_SHORT).show();
        }

    }

    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
           favListItem fav1=favListItems.get(viewHolder.getAdapterPosition());
            Toast.makeText(favouriteList.this, "Removed from Favourites", Toast.LENGTH_SHORT).show();
            Database.deleteTitle(fav1.getName());
            favListItems.remove(viewHolder.getAdapterPosition());
            favListAdapter.notifyDataSetChanged();

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(favouriteList.this, R.color.gray))
                    .addActionIcon(R.drawable.ic_bin)
                    .addSwipeLeftLabel("Remove ")
                    .setSwipeLeftLabelColor(getResources().getColor(R.color.red,null))
                    .setSwipeLeftLabelTextSize((int) TypedValue.COMPLEX_UNIT_DIP,15)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    public void filter(String s){
        List<favListItem> favListItems1=new ArrayList<>();
        for(favListItem f1:favListItems){
            if(f1.getName().toLowerCase().contains(s.toLowerCase())){
                favListItems1.add(f1);
            }
        }
        favListAdapter.filterList(favListItems1);
    }
}
