package com.example.logindatabase.ui.allProduct;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.logindatabase.Product;
import com.example.logindatabase.R;

import com.example.logindatabase.ui.syrup.SyrupRecylerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllProductFragment extends Fragment {

    private AllProductViewModel mViewModel;
    ArrayList<Product> productList;
    private DatabaseReference myRef;
    private RecyclerView recyclerView;
    private EditText searchProduct;

    private AllProductRecylerAdapter productRecylerAdapter;

    private Context mcontext;

    public static AllProductFragment newInstance() {
        return new AllProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.all_product_fragment, container, false);

        recyclerView=view.findViewById(R.id.recylerViewProduct);
        searchProduct=view.findViewById(R.id.inputSearchProduct);
        //variables


        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //firebase
        myRef= FirebaseDatabase.getInstance().getReference();

        //clear data
        clearAll();

        //get syrup
        GetDataFromFirebase("");

        //get data from topping
        getTopping();

        //get powder
        getPowder();

        //search for data
        searchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return view;
    }

    private void getPowder() {

        Query query=myRef.child("Product").child("Powder");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Product syrup=new Product();

                    syrup.setProductImage(snapshot.child("powderImage").getValue().toString());
                    syrup.setProductName(snapshot.child("powderName").getValue().toString());
                    syrup.setProductPrice(snapshot.child("powderPrice").getValue().toString());
                    syrup.setProductNum(snapshot.child("powderNum").getValue().toString());
                    syrup.setProductTitle(snapshot.child("powderTitle").getValue().toString());

                    //add to list
                    productList.add(syrup);
                }

                productRecylerAdapter = new AllProductRecylerAdapter(getContext(),productList);
                recyclerView.setAdapter(productRecylerAdapter);
                productRecylerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getTopping() {
        Query query=myRef.child("Product").child("Topping");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Product syrup=new Product();

                    syrup.setProductImage(snapshot.child("toppingImage").getValue().toString());
                    syrup.setProductName(snapshot.child("toppingName").getValue().toString());
                    syrup.setProductPrice(snapshot.child("toppingPrice").getValue().toString());
                    syrup.setProductNum(snapshot.child("toppingNum").getValue().toString());
                    syrup.setProductTitle(snapshot.child("toppingTitle").getValue().toString());

                    //add to list
                    productList.add(syrup);
                }

                productRecylerAdapter = new AllProductRecylerAdapter(getContext(),productList);
                recyclerView.setAdapter(productRecylerAdapter);
                productRecylerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void GetDataFromFirebase(String s) {
        Query query=myRef.child("Product").child("Syrup");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Product syrup=new Product();

                    syrup.setProductImage(snapshot.child("syrupImage").getValue().toString());
                    syrup.setProductName(snapshot.child("syrupName").getValue().toString());
                    syrup.setProductPrice(snapshot.child("syrupPrice").getValue().toString());
                    syrup.setProductNum(snapshot.child("syrupNum").getValue().toString());
                    syrup.setProductTitle(snapshot.child("syrupTitle").getValue().toString());

                    //add to list
                    productList.add(syrup);
                }

                productRecylerAdapter = new AllProductRecylerAdapter(getContext(),productList);
                recyclerView.setAdapter(productRecylerAdapter);
                productRecylerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void clearAll() {
        if(productList!=null){
            productList.clear();

            if(productRecylerAdapter !=null){
                productRecylerAdapter.notifyDataSetChanged();
            }

        }
        productList= new ArrayList<>();
    }

    private void filter(String text) {
        ArrayList<Product> filterList=new ArrayList<>();
        for(Product item : productList){
            if(item.getProductName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }

        productRecylerAdapter.filteredList(filterList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllProductViewModel.class);
        // TODO: Use the ViewModel
    }

}