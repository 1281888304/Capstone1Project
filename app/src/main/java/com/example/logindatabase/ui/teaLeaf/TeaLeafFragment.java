package com.example.logindatabase.ui.teaLeaf;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logindatabase.R;

public class TeaLeafFragment extends Fragment {

    private TeaLeafViewModel mViewModel;

    public static TeaLeafFragment newInstance() {
        return new TeaLeafFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tea_leaf_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TeaLeafViewModel.class);
        // TODO: Use the ViewModel
    }

}