package com.example.logindatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    ListView lvSummary;
    TextView tvTotal;
    Double Total = 0.0;

    Double Total1 = 0d;
    Double Total2 = 0d;

    ArrayList<Product> productOrders = new ArrayList<>();
    ArrayList<Product> orderedList = new ArrayList<>();

    String strValue1;

    Button backButton;
    
    private static final String TAG = "SummaryActivity";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This line hides the action bar
        
        setContentView(R.layout.activity_summary);
        lvSummary = findViewById(R.id.lvSummary);
        tvTotal = findViewById(R.id.tvTotal);



        backButton = (Button) findViewById(R.id.summary_backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickBackIntent = new Intent(Summary.this, Shop.class);
                startActivity(clickBackIntent);
            }
        });
        getOrderItemData();
        getOrderItemData2();

        resultSummary();

    }

//    private void getOrderItemData() {
//        //Bundle is basically a mapping from String values to various Parcelable types
//        Bundle extras = getIntent().getExtras();
//
//        if (extras != null) {
//            String orderItems = extras.getString("orderItems", null);
//
//
//            if (orderItems != null && orderItems.length() > 0) {
//                try {
//                    JSONArray jsonOrderItems = new JSONArray(orderItems);
//                    for (int i = 0; i < jsonOrderItems.length(); i++) {
//                        JSONObject jsonObject = new JSONObject(jsonOrderItems.getString(i));
//                        Product product = new Product(
//                                jsonObject.getString("ProductName")
//                                , jsonObject.getDouble("ProductPrice")
//                                , jsonObject.getInt("ProductImage")
//                        );
//                        product.CartQuantity = jsonObject.getInt("CartQuantity");
//                        /* Calculate Total */
//                        Total = Total + (product.CartQuantity * product.ProductPrice);
//                        productOrders.add(product);
//
//                    }
//
//                    if (productOrders.size() > 0) {
//                        ListAdapter listAdapter = new ListAdapter(this, productOrders);
//                        lvSummary.setAdapter(listAdapter);
//                        tvTotal.setText("Order Total: " + Total);
//                    } else {
//                        showMessage("Your cart is empty");
//                    }
//                } catch (Exception e) {
//                    showMessage(e.toString());
//                }
//            }
//
//        }
//    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }
    
    
    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
    
    private void getOrderItemData() {
        //Bundle is basically a mapping from String values to various Parcelable types
        //Bundle extras = getIntent().getExtras();
        SharedPreferences sharedPreferences = getSharedPreferences("prefTea",MODE_PRIVATE);


        if (sharedPreferences != null) {
            Log.d(TAG, "getOrderItemData: ");
            //String orderItems = extras.getString("orderItems", null);
            String orderItems = sharedPreferences.getString("orderItems","");


            if (orderItems != null && orderItems.length() > 0) {
                try {
                    JSONArray jsonOrderItems = new JSONArray(orderItems);
                    for (int i = 0; i < jsonOrderItems.length(); i++) {
                        JSONObject jsonObject = new JSONObject(jsonOrderItems.getString(i));
                        Product product = new Product(
                                jsonObject.getString("ProductName")
                                , jsonObject.getDouble("ProductPrice")
                                , jsonObject.getInt("ProductImage")
                        );
                        product.CartQuantity = jsonObject.getInt("CartQuantity");
                        /* Calculate Total */
                        Total1 = Total1 + (product.CartQuantity * product.ProductPrice);

                        BigDecimal bigOne = new BigDecimal(Double.toString(Total1));
                        int scale = 2;
                        bigOne = bigOne.setScale(scale,BigDecimal.ROUND_HALF_EVEN);
                        strValue1 = bigOne.stripTrailingZeros().toPlainString();



                        productOrders.add(product);

                     orderedList = new ArrayList<>();
                        for(Product p : productOrders){
                            orderedList.add(p);

                        }


//                       sp = getSharedPreferences("PREFS",MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sp.edit();
//                        editor.putString("words", String.valueOf(productOrders));
//                        editor.apply();

                    }

                } catch (Exception e) {
                    showMessage(e.toString());
                }
            }

        }
    }


    private void getOrderItemData2() {
        //Bundle is basically a mapping from String values to various Parcelable types
        //Bundle extras2 = getIntent().getExtras();

        SharedPreferences sharedPreferences = getSharedPreferences("prefSyrup",MODE_PRIVATE);


        if (sharedPreferences != null) {
            Log.d(TAG, "getOrderItemData2: ");
            String orderItems2 = sharedPreferences.getString("orderItems2","");

            if (orderItems2 != null && orderItems2.length() > 0) {
                try {
                    JSONArray jsonOrderItems2 = new JSONArray(orderItems2);
                    for (int i = 0; i < jsonOrderItems2.length(); i++) {
                        JSONObject jsonObject2 = new JSONObject(jsonOrderItems2.getString(i));
                        Product product2 = new Product(
                                jsonObject2.getString("ProductName")
                                , jsonObject2.getDouble("ProductPrice")
                                , jsonObject2.getInt("ProductImage")
                        );
                        product2.CartQuantity = jsonObject2.getInt("CartQuantity");
                        /* Calculate Total */
                        Total2 = Total2 + (product2.CartQuantity * product2.ProductPrice);
                        orderedList.add(product2);



                    }

                } catch (Exception e) {
                    showMessage(e.toString());
                }
            }

        }
    }



    public void resultSummary(){

        Log.d(TAG, "resultSummary: ");
         Total = Total1 + Total2;

        if (orderedList.size() > 0) {
            ListAdapter listAdapter = new ListAdapter(this, orderedList);
            lvSummary.setAdapter(listAdapter);
            tvTotal.setText("Order Total: " + strValue1);
        } else {
            showMessage("Your cart is empty");
        }

    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}