package com.example.logindatabase;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    ListView lvSummary;
    TextView tvTotal;
    Double Total=0d;
    ArrayList<Product> productOrders = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This line hides the action bar
        setContentView(R.layout.activity_summary);
        lvSummary = findViewById(R.id.lvSummary);
        tvTotal = findViewById(R.id.tvTotal);

        getOrderItemData();

    }


    private void getOrderItemData() {
        //Bundle is basically a mapping from String values to various Parcelable types
        Bundle extras = getIntent().getExtras();
        if(extras != null )
        {
            String orderItems = extras.getString("orderItems",null);
            if(orderItems!=null && orderItems.length()>0)
            {
                try{
                    JSONArray jsonOrderItems = new JSONArray(orderItems);
                    for(int i=0;i<jsonOrderItems.length();i++)
                    {
                        JSONObject jsonObject = new JSONObject(jsonOrderItems.getString(i));
                        Product product = new Product(
                                jsonObject.getString("ProductName")
                                ,jsonObject.getDouble("ProductPrice")
                                ,jsonObject.getInt("ProductImage")
                        );
                        product.CartQuantity = jsonObject.getInt("CartQuantity");
                        /* Calculate Total */
                        Total = Total + (product.CartQuantity * product.ProductPrice);
                        productOrders.add(product);
                    }

                    if(productOrders.size() > 0)
                    {
                        ListAdapter listAdapter = new ListAdapter(this,productOrders);
                        lvSummary.setAdapter(listAdapter);
                        tvTotal.setText("Order Total: "+Total);
                    }
                    else
                    {
                        showMessage("Your cart is empty");
                    }
                }
                catch (Exception e)
                {
                    showMessage(e.toString());
                }
            }

        }
    }

    public void showMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}