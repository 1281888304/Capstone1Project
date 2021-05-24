package com.example.logindatabase.ui.history;

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

import com.example.logindatabase.R;
import com.example.logindatabase.ui.shoppingCart.CartProduct;
import com.example.logindatabase.ui.shoppingCart.OrderProduct;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    View v;

    private ArrayList<OrderProduct> orderList;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference ordersRef = db.getReference().child("orders");
    private RecyclerView historyRecyclerView;
    private HistoryRecyclerAdapter historyAdapter;
    private Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.history_fragment, container, false);

//        historyRecyclerView = v.findViewById(R.id.HistoryRecylerView);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//        historyRecyclerView.setLayoutManager(layoutManager);
//        historyRecyclerView.setHasFixedSize(true);
//
//        orderList = new ArrayList<>();
//                        historyAdapter = new HistoryRecyclerAdapter(getContext(), orderList);
//                        historyRecyclerView.setAdapter(historyAdapter);
//                        historyAdapter.notifyDataSetChanged();

        return v;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        historyRecyclerView = v.findViewById(R.id.HistoryRecylerView);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//        historyRecyclerView.setLayoutManager(layoutManager);
//        historyRecyclerView.setHasFixedSize(true);
//
//        orderList = new ArrayList<>();
//                        historyAdapter = new HistoryRecyclerAdapter(getContext(), orderList);
//                        historyRecyclerView.setAdapter(historyAdapter);
//                        historyAdapter.notifyDataSetChanged();
//
//    }


}