package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.entry;
import com.example.model.majorSpecie;
import com.example.model.pokedex;
import com.example.model.pokemon;
import com.example.network.RetrofitClientInstance;
import com.example.network.getDataInstance;
import com.example.network.getSpecies;
import com.example.network.getsecondInstance;

import java.util.ArrayList;
import java.util.List;

public class regionList extends AppCompatActivity {
    private static final String TAG = "regionList";
    List<pokedex> pokedexList=new ArrayList<>();
    List<pokemon> pokemonList;
    pokemon pokemon1;
    boolean flag=true;
    List<List<pokemon>> region1=new ArrayList<>();
    EditText editText;

    RecyclerView recyclerView;
    regionAdapter regionAdapter;
    Integer k=0;
    ProgressBar progressBar;
    Integer btnVal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_list2);

        ImageView front=findViewById(R.id.front1);
        ImageView back=findViewById(R.id.back1);


        editText=findViewById(R.id.regionSearch);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    editText.setHint("");
                }else{
                    editText.setHint("Search");
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterList(editable.toString());
            }
        });


        progressBar=findViewById(R.id.progressBar2);
        Drawable drawable=getDrawable(R.drawable.circular);
        progressBar.setProgressDrawable(drawable);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setClickable(false);

        setPager();
         final getsecondInstance getsecondInstanceS = RetrofitClientInstance.getRetrofit().create(com.example.network.getsecondInstance.class);
        for (int i = 1; i < 4; i++) {
            k = 1;
            Call<pokedex> pokedexCall = getsecondInstanceS.getPokedexes(i);
            Log.d(TAG, "onCreate: ");
            pokedexCall.enqueue(new Callback<pokedex>() {
                @Override
                 public void onResponse(Call<pokedex> call, Response<pokedex> response) {
                    if (k != 10 && k != 23 && k != 6 && k != 7 && k!=8) {
                        Log.d(TAG, "onResponse: " + k + "," + response.body().getName());
                        pokedexList.add(response.body());

                        callVariety(response.body().getPokemon_entry());
                        regionGenerate(region1,pokedexList);

                    }
                    k++;
                }

                @Override
                public void onFailure(Call<pokedex> call, Throwable t) {
                    Toast.makeText(regionList.this, "Network Error", Toast.LENGTH_SHORT).show();
                }

            });

        }




        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               if(btnVal==2){
                   Toast.makeText(regionList.this,"Last Page",Toast.LENGTH_SHORT).show();
                } else {

                   progressBar.setVisibility(View.VISIBLE);
                   pokedexList.clear();
                   region1.clear();
                   btnVal++;
                   setPager();
                    for (int i = 3 * btnVal + 1; i <= 3 * btnVal + 3; i++) {
                        k = 1;
                        getsecondInstance getsecondInstance1 = RetrofitClientInstance.getRetrofit().create(getsecondInstance.class);
                        final Call<pokedex> pokedexCall = getsecondInstance1.getPokedexes(i);
                        Log.d(TAG, "onCreate: ");
                        pokedexCall.enqueue(new Callback<pokedex>() {
                            @Override
                            public void onResponse(Call<pokedex> call, Response<pokedex> response) {
                                if (k != 10 && k != 23 && k != 6 && k != 7 && k != 8) {
                                    Log.d(TAG, "onResponse: " + k + "," + response.body().getName());
                                    pokedexList.add(response.body());

                                    callVariety(response.body().getPokemon_entry());
                                    regionGenerate(region1, pokedexList);

                                }
                                k++;
                            }

                            @Override
                            public void onFailure(Call<pokedex> call, Throwable t) {
                                Toast.makeText(regionList.this, "Network Error", Toast.LENGTH_SHORT).show();
                            }

                        });

                        editText.setText("");
                    editText.setHint("Search");}
                }
            }
    });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnVal == 0) {
                    Toast.makeText(regionList.this, "No Previous Page", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    pokedexList.clear();
                    region1.clear();
                    btnVal--;
                    setPager();
                    for(int i=3*btnVal+1;i<=3*btnVal+3;i++){
                        k = 1;
                        getsecondInstance getsecondInstance2=RetrofitClientInstance.getRetrofit().create(getsecondInstance.class);
                        final Call<pokedex> pokedexCall = getsecondInstance2.getPokedexes(i);
                        Log.d(TAG, "onCreate: ");
                        pokedexCall.enqueue(new Callback<pokedex>() {
                            @Override
                            public void onResponse(Call<pokedex> call, Response<pokedex> response) {
                                if ( k != 10 && k != 23 && k != 6 && k != 7 && k!=8) {
                                    Log.d(TAG, "onResponse: " + k + "," + response.body().getName());
                                    pokedexList.add(response.body());

                                    callVariety(response.body().getPokemon_entry());
                                    regionGenerate(region1,pokedexList);

                                }
                                k++;
                            }

                            @Override
                            public void onFailure(Call<pokedex> call, Throwable t) {
                                Toast.makeText(regionList.this, "Network Error", Toast.LENGTH_SHORT).show();
                            }

                        });

                        editText.setText("");
                        editText.setHint("Search");}
                }


            }
        });
    }

        public void callVariety(List<entry> entryList){
        final List<pokemon> regionList=new ArrayList<>();
        for(int i=0;i<24;i+=3) {
            String url1=entryList.get(i).getSpecies1().getUrl();
            String url2=entryList.get(i+1).getSpecies1().getUrl();
            String url3=entryList.get(i+2).getSpecies1().getUrl();
            getSpecies getSpeciesInstance = RetrofitClientInstance.getRetrofit().create(getSpecies.class);
            getSpecies getSpeciesInastance1 = RetrofitClientInstance.getRetrofit().create(getSpecies.class);
            getSpecies getSpeciesInstance2 = RetrofitClientInstance.getRetrofit().create(getSpecies.class);
            Call<majorSpecie> majorSpecieCall = getSpeciesInstance.getSpecieVariety(Integer.parseInt(url1.substring(42, url1.length() - 1)));
            majorSpecieCall.enqueue(new Callback<majorSpecie>() {
                @Override
                public void onResponse(Call<majorSpecie> call, Response<majorSpecie> response) {
                    Log.d(TAG, "callVariety: " + response.body().getVarieties().get(0).getPokemon().getName());
                    getDataInstance dataInstance=RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
                    String url=response.body().getVarieties().get(0).getPokemon().getUrl();
                    Call<pokemon> pokemonCall = dataInstance.getInfo(Integer.parseInt(url.substring(34, url.length() - 1)));
                    pokemonCall.enqueue(new Callback<pokemon>() {
                        @Override
                        public void onResponse(Call<pokemon> call, Response<pokemon> response) {
                            Log.d(TAG, "onResponsename: " + response.body().getName());
                            regionList.add(response.body());
                        }

                        @Override
                        public void onFailure(Call<pokemon> call, Throwable t) {
                            pokemon1 = null;
                        }
                    });

                }

                @Override
                public void onFailure(Call<majorSpecie> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });

            Call<majorSpecie> majorSpecieCall1 = getSpeciesInastance1.getSpecieVariety(Integer.parseInt(url2.substring(42, url2.length() - 1)));
            majorSpecieCall1.enqueue(new Callback<majorSpecie>() {
                @Override
                public void onResponse(Call<majorSpecie> call, Response<majorSpecie> response) {
                    Log.d(TAG, "oncallVariety1: " + response.body().getVarieties().get(0).getPokemon().getName());
                    getDataInstance dataInstance=RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
                    String url=response.body().getVarieties().get(0).getPokemon().getUrl();
                    Call<pokemon> pokemonCall = dataInstance.getInfo(Integer.parseInt(url.substring(34, url.length() - 1)));
                    pokemonCall.enqueue(new Callback<pokemon>() {
                        @Override
                        public void onResponse(Call<pokemon> call, Response<pokemon> response) {
                            Log.d(TAG, "onResponsename: " + response.body().getName());
                            regionList.add(response.body());
                        }

                        @Override
                        public void onFailure(Call<pokemon> call, Throwable t) {
                            pokemon1 = null;
                        }
                    });

                }


                @Override
                public void onFailure(Call<majorSpecie> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });


            Call<majorSpecie> majorSpecieCall2 = getSpeciesInstance2.getSpecieVariety(Integer.parseInt(url3.substring(42, url3.length() - 1)));
            majorSpecieCall2.enqueue(new Callback<majorSpecie>() {
                @Override
                public void onResponse(Call<majorSpecie> call, Response<majorSpecie> response) {
                    Log.d(TAG, "oncallVariety1: " + response.body().getVarieties().get(0).getPokemon().getName());
                    getDataInstance dataInstance=RetrofitClientInstance.getRetrofit().create(getDataInstance.class);
                    String url=response.body().getVarieties().get(0).getPokemon().getUrl();
                    Call<pokemon> pokemonCall = dataInstance.getInfo(Integer.parseInt(url.substring(34, url.length() - 1)));
                    pokemonCall.enqueue(new Callback<pokemon>() {
                        @Override
                        public void onResponse(Call<pokemon> call, Response<pokemon> response) {
                            Log.d(TAG, "onResponsename: " + response.body().getName());
                            regionList.add(response.body());
                        }

                        @Override
                        public void onFailure(Call<pokemon> call, Throwable t) {
                            pokemon1 = null;
                        }
                    });

                }

                @Override
                public void onFailure(Call<majorSpecie> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });

        }
        region1.add(regionList);
    }



        public void regionGenerate(List<List<pokemon>> specie,List<pokedex> pokedexList){
            if(this.pokedexList.size()>=3 ){

                progressBar.setVisibility(View.GONE);
            }

            recyclerView=findViewById(R.id.rec1);
            regionAdapter=new regionAdapter(specie,pokedexList,regionList.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(regionList.this));
            recyclerView.setAdapter(regionAdapter);

        }

    public void filterList(String s){
        List<pokedex> filterList=new ArrayList<>();
        for(pokedex p:pokedexList){
            if(p.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(p);
            }
        }
        regionAdapter.filterList(filterList);

    }
    public void setPager(){
        TextView pager=findViewById(R.id.pager1);
        pager.setText("Page: "+ (btnVal+1)+"/3");
    }


}



