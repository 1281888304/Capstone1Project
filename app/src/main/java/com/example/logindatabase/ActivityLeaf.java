package com.example.logindatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.ArrayList;

public class ActivityLeaf extends AppCompatActivity {

    private ImageView logoLeaf;

    private ListView listView;
    private ListAdapter listAdapter;
    ArrayList<Product> products = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<Product> productOrders = new ArrayList<>();
    ArrayList<String> lOrderItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This line hides the action bar

        setContentView(R.layout.activity_leaf);

        logoLeaf = (ImageView)findViewById(R.id.logoleaf);
        logoLeaf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                backToPage();
            }
        });

        getProduct();
        listView = (ListView) findViewById(R.id.customListView);

        //put products into this activity via adapter
        listAdapter = new ListAdapter(this,products);
        listView.setAdapter(listAdapter);

        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                placeOrder();

            }
        });


    }


    private void placeOrder()
    {
        productOrders.clear();
        lOrderItems.clear();

        for(int i=0;i<listAdapter.listProducts.size();i++)
        {
            if(listAdapter.listProducts.get(i).CartQuantity > 0)
            {
                Product products = new Product(
                        listAdapter.listProducts.get(i).ProductName
                        ,listAdapter.listProducts.get(i).ProductPrice
                        ,listAdapter.listProducts.get(i).ProductImage
                );
                products.CartQuantity = listAdapter.listProducts.get(i).CartQuantity;
                productOrders.add(products);
                lOrderItems.add(products.getJsonObject());

            }
        }
        //We can pass this JSONArray into another activity
        JSONArray jsonArray = new JSONArray(lOrderItems);
        jsonArray.toString();

        openSummary(jsonArray.toString());


    }

    public void openSummary(String orderItems)
    {
        Intent summaryIntent = new Intent(this,Summary.class);
        summaryIntent.putExtra("orderItems",orderItems);
        startActivity(summaryIntent);
    }

    public void showMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    public void getProduct() {
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));
        products.add(new Product("Fruit Tea",10.0d,R.drawable.fruittea));

    }


    public void backToPage(){

        Intent clickBackIntent = new Intent(ActivityLeaf.this, MainActivity.class);
        startActivity(clickBackIntent);

    }
}