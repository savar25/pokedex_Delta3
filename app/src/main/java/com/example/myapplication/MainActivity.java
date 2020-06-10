package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.model.pokemon;
import com.example.network.RetrofitClientInstance;
import com.example.network.getDataInstance;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static int SPLASH_TIME = 2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Choice.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);




    }
}