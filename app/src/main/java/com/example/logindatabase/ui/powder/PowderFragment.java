package com.example.logindatabase.ui.powder;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.logindatabase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PowderFragment extends Fragment {

    private PowderViewModel mViewModel;
    ArrayList<Powders> powderList;
    private DatabaseReference myRef;
    private RecyclerView recyclerView;

    //adapter
    private PowderRecylerAdapter powderRecylerAdapter;

    private Context mcontext;

    public static PowderFragment newInstance() {
        return new PowderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.powder_fragment, container, false);

        recyclerView=view.findViewById(R.id.recylerViewPowder);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //firebase
        myRef= FirebaseDatabase.getInstance().getReference();


        //clear data
        clearAll();

        //get data model
        GetDataFromFirebase("");



        return view;
    }

    private void GetDataFromFirebase(String s) {
        Query query=myRef.child("Product").child("Powder");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Powders powders=new Powders();

                    powders.setPowderImage(snapshot.child("powderImage").getValue().toString());
                    powders.setPowderName(snapshot.child("powderName").getValue().toString());
                    powders.setPowderPrice(snapshot.child("powderPrice").getValue().toString());
                    powders.setPowderNum(snapshot.child("powderNum").getValue().toString());
                    powders.setPowderTitle(snapshot.child("powderTitle").getValue().toString());

                    //add to list
                    powderList.add(powders);
                }

                powderRecylerAdapter = new PowderRecylerAdapter(getContext(),powderList);
                recyclerView.setAdapter(powderRecylerAdapter);
                powderRecylerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void clearAll() {
        if(powderList!=null){
            powderList.clear();

            if(powderRecylerAdapter !=null){
                powderRecylerAdapter.notifyDataSetChanged();
            }

        }
        powderList= new ArrayList<>();
        
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PowderViewModel.class);
        // TODO: Use the ViewModel
    }

}