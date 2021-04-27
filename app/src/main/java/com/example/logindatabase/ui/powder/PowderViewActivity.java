package com.example.logindatabase.ui.powder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class PowderViewActivity extends AppCompatActivity {

    private ImageView powderImageCLick;
    private TextView powderNameClick;
    private TextView powderPriceClick;

    //btn
    private Button powderAdd,powderDrop;
    private TextView powderNumClick;
    private TextView powderTitleCLick;

    private DatabaseReference myRef; //use to change data

    private View powderClickView;

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
        setTitle("Powders Detail");
        setContentView(R.layout.activity_powder_view);

        powderImageCLick=findViewById(R.id.powderImageClick);
        powderNameClick=findViewById(R.id.powderNameClick);
        powderPriceClick=findViewById(R.id.powderPriceClick);
        powderAdd=findViewById(R.id.powderAdd);
        powderDrop=findViewById(R.id.powderDelete);
        powderNumClick=findViewById(R.id.powderNumClick);
        powderTitleCLick=findViewById(R.id.powderTitleClick);

        //reference
        ref= FirebaseDatabase.getInstance().getReference().child("Product").child("Powder");
        //cart data
        cartRef=FirebaseDatabase.getInstance().getReference().child("Cart");

        // the currentPowder
        String powderTitle =getIntent().getStringExtra("productKey");
        String currentPowder=powderTitle;

        //get user detail
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        if(getIntent().getStringExtra("productNum").isEmpty() ||
                getIntent().getStringExtra("productNum")==null){
            orderNumString="0";
        }else{
            orderNumString=getIntent().getStringExtra("productNum");
        }

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

        //get the data
        getData(powderTitle,orderNumString);

        powderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=Integer.parseInt(orderNumString);
                num++;
                orderNumString=String.valueOf(num);
                getData(powderTitle,orderNumString);
            }
        });

        powderDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=Integer.parseInt(orderNumString);
                if(num>0){
                    num--;
                }
                orderNumString=String.valueOf(num);
                getData(powderTitle,orderNumString);
            }
        });
    }

    private void getData(String powderTitle, String orderNumString) {
        ref.child(powderTitle).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String imageUrl=dataSnapshot.child("powderImage").getValue().toString();
                    String name=dataSnapshot.child("powderName").getValue().toString();

                    String price=dataSnapshot.child("powderPrice").getValue().toString();
                    String title=dataSnapshot.child("powderTitle").getValue().toString();

                    //set value
                    Picasso.get().load(imageUrl).into(powderImageCLick);
                    powderNameClick.setText(name);
                    powderNumClick.setText("Order Number: "+orderNumString);
                    powderPriceClick.setText(price);
                    powderTitleCLick.setText(title);

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