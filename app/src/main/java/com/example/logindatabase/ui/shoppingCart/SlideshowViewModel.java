package com.example.logindatabase.ui.shoppingCart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CartProduct>> cartProduct;

    private ArrayList<CartProduct> ordersInCart;

    public SlideshowViewModel() {
    //    mText = new MutableLiveData<>();
     //   mText.setValue("This is slideshow fragment");
    }

//    public LiveData<String> getText() {
//       // return mText;
//    }


}