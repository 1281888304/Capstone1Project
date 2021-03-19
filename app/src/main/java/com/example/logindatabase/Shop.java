package com.example.logindatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {

    ImageButton buttonLeaf;
    ImageButton buttonEquip;
    ImageButton buttonSyrup;
    ImageButton buttonCup;

    private ListView listView;
    private ListAdapter listAdapter;
    ArrayList<Product> products = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<Product> productOrders = new ArrayList<>();
    ArrayList<String> lOrderItems = new ArrayList<>();

    private static final String TAG = "MainActivity";

 // test branch
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: in");

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_shop);

        buttonLeaf = (ImageButton) findViewById(R.id.imageButton4);
        buttonSyrup = (ImageButton) findViewById(R.id.imageButton5);
        buttonCup = (ImageButton) findViewById(R.id.imageButton6);
        buttonEquip = (ImageButton) findViewById(R.id.imageButton7);

        buttonLeaf.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                openActivity4();
            }

        });


        buttonSyrup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                openActivity5();

            }
        });

        buttonCup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                openActivity6();

            }

        });

        buttonEquip.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                openActivity7();

            }

        });

        Log.d(TAG, "onCreate: out");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.d(TAG, "onRestoreInstanceState: in");
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: out");
    }


    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: in");
        super.onResume();
        Log.d(TAG, "onResume: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        Log.d(TAG, "onSaveInstanceState: in");
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: out");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: in");
        super.onDestroy();
        Log.d(TAG, "onDestroy: out");
    }


    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        return super.checkUriPermission(uri, pid, uid, modeFlags);
    }


    public void openActivity4(){
        Intent clickLeafIntent = new Intent(Shop.this, ActivityLeaf.class);
        startActivity(clickLeafIntent);
    }



    public void openActivity5(){
        Intent clickSyrupIntent = new Intent(Shop.this, ActivitySyrup.class);
        startActivity( clickSyrupIntent);
    }

    public void openActivity6(){
        Intent clickCupIntent = new Intent(Shop.this, ActivityCup.class);
        startActivity(clickCupIntent);
    }


    public void openActivity7(){
        Intent clickEquipIntent = new Intent(Shop.this, ActivityEquip.class);
        startActivity(clickEquipIntent);
    }

}