package com.example.logindatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowOrders extends AppCompatActivity {

    RecyclerView recyclerView;

    private DatabaseReference myRef;

    private ArrayList<OrderShow> orderList;

    private RecylerAdapter recylerAdapter;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_orders);

        recyclerView=findViewById(R.id.recyleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Firebase
        myRef = FirebaseDatabase.getInstance().getReference();

        //get data arraylist
        orderList=new ArrayList<>();

        //clear data
        ClearAll();

        //get data method
        GetDataFirebase();



    }

    private void GetDataFirebase() {
        Query query=myRef.child("orders");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot:  dataSnapshot.getChildren()){
                    OrderShow orders=new OrderShow();
                    orders.setAddress(snapshot.child("address").getValue().toString());
                    orders.setCompany(snapshot.child("company").getValue().toString());
                    orders.setItem(snapshot.child("item").getValue().toString());
                    orders.setOrder_num(snapshot.child("order_num").getValue().toString());
                    orders.setQuantity(snapshot.child("quantity").getValue().toString());
                    orders.setStatus(snapshot.child("status").getValue().toString());

                    orderList.add(orders);
                }
                    recylerAdapter=new RecylerAdapter(getApplicationContext(),orderList);
                    recyclerView.setAdapter(recylerAdapter);
                    recylerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void ClearAll(){
//        if(!orderList.isEmpty()){
//
//        }
        if(orderList!=null){
            orderList.clear();

            if(recylerAdapter!=null){
                recylerAdapter.notifyDataSetChanged();
            }
        }

        orderList=new ArrayList<>();
    }
}