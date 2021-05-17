package com.example.logindatabase.ui.allProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logindatabase.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllProductViewActivity extends AppCompatActivity {

    private ImageView productImageClick;
    private TextView productNameClick;
    private TextView productPriceClick;

    //btn
    private Button productAddClick,productDropClick;
    private TextView productNumClick;
    private TextView productTitleClick;

    private DatabaseReference myRef; //use to change data

    private View productClickView;

    private DatabaseReference ref,cartRef;

    private String orderNumString;

    //get User detail
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private String userName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Syrup Detail");
        setTitleColor(Color.rgb(200,0,0));
        setContentView(R.layout.activity_all_product_view);

        productImageClick=findViewById(R.id.productImageClick);
        productNameClick=findViewById(R.id.productNameClick);
        productPriceClick=findViewById(R.id.productPriceClick);
        productAddClick=findViewById(R.id.productShopAddClick);
        productDropClick=findViewById(R.id.productShopDeleteClick);
        productNumClick=findViewById(R.id.productOrderNumClick);
        productTitleClick=findViewById(R.id.productTitleCLick);

        ref= FirebaseDatabase.getInstance().getReference().child("Product").child("Syrup");
        //cart data
        cartRef=FirebaseDatabase.getInstance().getReference().child("Cart");




    }
}