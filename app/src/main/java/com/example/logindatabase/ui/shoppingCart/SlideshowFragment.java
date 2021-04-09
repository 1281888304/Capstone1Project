package com.example.logindatabase.ui.shoppingCart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.logindatabase.R;
import com.example.logindatabase.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    //basic field
    private SlideshowViewModel slideshowViewModel;
    private ArrayList<CartProduct> cartList;
    private DatabaseReference myRef,priceRef;

    //widget
    private RecyclerView cartRecyclerView;
    private TextView totalPrice;
    private Button contract;

    //adapter
    private CartRecylerAdapter cartRecylerAdapter;
    //get User detail
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

     private String userName="";





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        //add buttom here
        cartRecyclerView=view.findViewById(R.id.cartRecylerView);
        totalPrice=view.findViewById(R.id.cartTotalPrice);
        contract=view.findViewById(R.id.cart_BtnContract);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setHasFixedSize(true);

        //firebase
        myRef= FirebaseDatabase.getInstance().getReference();

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
                    clearAll();

                    getData();

                    countPrice();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getClass().this,"Something wrong on our app",Toast.LENGTH_LONG).show();
            }
        });


        contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }



    private void countPrice() {

        priceRef=FirebaseDatabase.getInstance().getReference().child("Cart").child(userName);
        priceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double sum=0;
                String test="";
                for (DataSnapshot ds : snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object price =map.get("cartProductPrice");
                    //split the string
                    String s=String.valueOf(price);
                    String[] arrayString=s.split("/");
                    double priceValue=Double.parseDouble(String.valueOf(arrayString[0]));


                    //get the ordernumber
                    Map<String,Object> map2=(Map<String, Object>) ds.getValue();
                    Object num=map.get("cartProductNum");
                    String str=String.valueOf(num);
                    int orderNum=0;
                    orderNum=Integer.parseInt(str);



                    sum=sum+(priceValue*orderNum);

                }
                totalPrice.setText("Total Price is $"+String.valueOf(String.valueOf(sum)));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getData() {
        Query query=myRef.child("Cart").child(userName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    CartProduct cartProduct=new CartProduct();

                    cartProduct.setCartProductName(snapshot.child("cartProductName").getValue().toString());
                    cartProduct.setCartProductNum(snapshot.child("cartProductNum").getValue().toString());
                    cartProduct.setCartProductPrice(snapshot.child("cartProductPrice").getValue().toString());
                    cartProduct.setCartProductTitle(snapshot.child("cartProductTitle").getValue().toString());

                    cartList.add(cartProduct);
                }
                cartRecylerAdapter=new CartRecylerAdapter(getContext(),cartList);
                cartRecyclerView.setAdapter(cartRecylerAdapter);
                cartRecylerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearAll() {
        if(cartList!=null){
            cartList.clear();

            if(cartRecylerAdapter !=null){
                cartRecylerAdapter.notifyDataSetChanged();
            }

        }
        cartList= new ArrayList<>();
    }
}