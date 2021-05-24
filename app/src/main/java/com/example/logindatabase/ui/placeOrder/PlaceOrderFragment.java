package com.example.logindatabase.ui.placeOrder;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.logindatabase.R;
import com.example.logindatabase.ui.history.HistoryFragment;

public class PlaceOrderFragment extends Fragment {

    private Button btnPlaceOrder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_order_fragment, container, false);

        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                FragmentTransaction fr = getChildFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container_new,new HistoryFragment());
                fr.commit();
            }

        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    //    mViewModel = new ViewModelProvider(this).get(PlaceOrderViewModel.class);
        // TODO: Use the ViewModel



    }

}