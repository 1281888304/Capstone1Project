package com.example.logindatabase.ui.syrup;

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


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SyrupFragment extends Fragment {

    private SyrupViewModel mViewModel;
    ArrayList<Product> syrupList;
    private DatabaseReference myRef;
    private RecyclerView recyclerView;
    private EditText searchSyrup;

    private SyrupRecylerAdapter syrupRecylerAdapter;

    private Context mcontext;

    public static SyrupFragment newInstance() {
        return new SyrupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.syrup_fragment, container, false);

//code here

        recyclerView=view.findViewById(R.id.recylerViewSyrup);
        searchSyrup=view.findViewById(R.id.inputSearchSyrup);
        //variables


        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //firebase
        myRef= FirebaseDatabase.getInstance().getReference();


        //clear data
        clearAll();

        //get data model
        GetDataFromFirebase("");





        //search for data
        searchSyrup.addTextChangedListener(new TextWatcher() {
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




    private void filter(String text) {


        ArrayList<Product> filterList=new ArrayList<>();
        for(Product item : syrupList){
            if(item.getProductName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }

        syrupRecylerAdapter.filteredList(filterList);
    }

    private void clearAll(){
        if(syrupList!=null){
            syrupList.clear();

            if(syrupRecylerAdapter !=null){
                syrupRecylerAdapter.notifyDataSetChanged();
            }

        }
        syrupList= new ArrayList<>();

    }

    private void GetDataFromFirebase(String data) {

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
                    syrupList.add(syrup);
                }

                syrupRecylerAdapter = new SyrupRecylerAdapter(getContext(),syrupList);
                recyclerView.setAdapter(syrupRecylerAdapter);
                syrupRecylerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SyrupViewModel.class);
        // TODO: Use the ViewModel
    }

}