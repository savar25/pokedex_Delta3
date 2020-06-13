package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.model.pokemon;
import com.google.gson.Gson;

public class sqlDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB1.db";
    public static final String POKE_NAME = "name";
    public static final String IMAGE_URL = "img_url";
    public static final String FIMAGE_URL = "fimg_url";
    public static final String FSIMAGE_URL = "fsimg_url";
    public static final String BIMAGE_URL = "bimg_url";
    public static final String BSIMAGE_URL = "bsimg_url";
    public static final String ABILITY = "ability";
    private static final String TAG = "sqlDatabase";


    public sqlDatabase(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table pokemon_n"+ "(name varchar primary key,img_url varchar,fimg_url varchar,fsimg_url varchar,bimg_url varchar,bsimg_url varchar,ability varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pokemon_n");
        onCreate(sqLiteDatabase);
    }

    public boolean addPoke(pokemon pokemon1){
        Log.d(TAG, "addPoke: added");
        SQLiteDatabase database=this.getWritableDatabase();

        Gson gson=new Gson();
        String inputString=gson.toJson(pokemon1.getAbility());


        ContentValues contentValues=new ContentValues();
        Log.d(TAG, "addPoke: "+pokemon1.getName()+","+pokemon1.getSprites().getFront_shiny());
        contentValues.put("name",pokemon1.getName());
        contentValues.put("img_url",pokemon1.getSprites().getFront_default());
        contentValues.put("fimg_url",pokemon1.getSprites().getFront_default());
        contentValues.put("fsimg_url",pokemon1.getSprites().getFront_shiny());
        contentValues.put("bimg_url",pokemon1.getSprites().getBack_default());
        contentValues.put("bsimg_url",pokemon1.getSprites().getBack_shiny());
        contentValues.put("ability",inputString);
        database.insert("pokemon_n",null,contentValues);
        return true;
    }

    public Cursor getPoke(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor value=database.rawQuery("select * from pokemon_n",null);
        return value;
    }

    public Cursor getNames(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor value=database.rawQuery("select name from pokemon_n",null);
        return value;
    }

    public void deleteTitle(String name)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("DELETE FROM pokemon_n WHERE "+"name "+" = '"+ name+"'");
    }
}
