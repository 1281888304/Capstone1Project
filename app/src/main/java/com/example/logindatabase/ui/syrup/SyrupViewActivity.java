package com.example.logindatabase.ui.syrup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logindatabase.R;
import com.example.logindatabase.User;
import com.example.logindatabase.ui.shoppingCart.CartProduct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SyrupViewActivity extends AppCompatActivity {

    private ImageView syrupImageClick;
    private TextView syrupNameClick;
    private TextView syrupPriceClick;

    //btn
    private Button syrupAddClick,syrupDropClick;
    private TextView syrupNumClick;
    private TextView syrupTitleClick;

    private DatabaseReference myRef; //use to change data

    private View syrupClickView;

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
        setContentView(R.layout.activity_syrup_view);

        syrupImageClick=findViewById(R.id.syrupImageClick);
        syrupNameClick=findViewById(R.id.syrupNameClick);
        syrupPriceClick=findViewById(R.id.syrupPriceClick);
        syrupAddClick=findViewById(R.id.syrupShopAddClick);
        syrupDropClick=findViewById(R.id.syrupShopDeleteClick);
        syrupNumClick=findViewById(R.id.syrupOrderNumClick);
        syrupTitleClick=findViewById(R.id.syrupTitleCLick);




        ref= FirebaseDatabase.getInstance().getReference().child("Product").child("Syrup");
        //cart data
        cartRef=FirebaseDatabase.getInstance().getReference().child("Cart");



        String syrupTitle =getIntent().getStringExtra("productKey");
        String currentSyrup=syrupTitle;

        //get user detail
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile !=null){
                    String fullName=userProfile.fullName;
                    userName=fullName;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getClass().this,"Something wrong on our app",Toast.LENGTH_LONG).show();
            }
        });

        if(getIntent().getStringExtra("productNum").isEmpty() ||
                getIntent().getStringExtra("productNum")==null){
            orderNumString="0";
        }else{
            orderNumString=getIntent().getStringExtra("productNum");
        }
        //get the data
        getData(syrupTitle,orderNumString);

        //Add here
        syrupAddClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=Integer.parseInt(orderNumString);
                num++;
                orderNumString=String.valueOf(num);
                getData(syrupTitle,orderNumString);
            }
        });

        syrupDropClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=Integer.parseInt(orderNumString);
                if(num>0){
                    num--;
                }
                orderNumString=String.valueOf(num);
                getData(syrupTitle,orderNumString);
            }
        });
    }

    private void getData(String syrupTitle, String orderNumString) {
        //get data here
        ref.child(syrupTitle).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String imageUrl=dataSnapshot.child("syrupImage").getValue().toString();
                    String name=dataSnapshot.child("syrupName").getValue().toString();

                    String price=dataSnapshot.child("syrupPrice").getValue().toString();
                    String title=dataSnapshot.child("syrupTitle").getValue().toString();

                    //set value
                    Picasso.get().load(imageUrl).into(syrupImageClick);
                    syrupNameClick.setText(name);
                    syrupNumClick.setText("Order Number: "+orderNumString);
                    syrupPriceClick.setText(price);
                    syrupTitleClick.setText(title);

                    int num=Integer.parseInt(orderNumString);
                    String numString=String.valueOf(num);
                    if(num>0){
                        //add to cart list firebase object
                        // first thing is check if it is exist
                        cartRef=FirebaseDatabase.getInstance().getReference()
                                .child("Cart").child(userName);
                        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(name)){
                                    cartRef.child(name).child("cartProductNum")
                                            .setValue(numString);
                                }else{
                                    //now add a new object into it
                                    cartRef=FirebaseDatabase.getInstance().getReference()
                                            .child("Cart");
                                    CartProduct cart=new CartProduct(name,numString,price,title);
                                    cartRef.child(userName).child(title).setValue(cart);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    //delete if num<0
                    if(num==0){
                        cartRef.child(userName).child(title).removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}