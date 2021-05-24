package com.example.logindatabase.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindatabase.R;
import com.example.logindatabase.ui.shoppingCart.OrderProduct;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>{

    //fields

    private Context mContext;
    private ArrayList<OrderProduct> orderList;


    public HistoryRecyclerAdapter(Context mContext, ArrayList<OrderProduct> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public HistoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_order_item,parent,false);


        return new HistoryRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecyclerAdapter.ViewHolder holder, int position) {

            OrderProduct orderProduct = orderList.get(position);
            holder.productName.setText(orderProduct.getProductName());
            holder.productNum.setText(orderProduct.getProductNum());
            holder.productPrice.setText(orderProduct.getProductPrice());
            holder.timeStamp.setText(orderProduct.getTimeStamp());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //widget
        TextView productName;
        TextView productNum;
        TextView productPrice;
        TextView timeStamp;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName=itemView.findViewById(R.id.history_ProductName);
            productNum=itemView.findViewById(R.id.history_Productnum);
            productPrice=itemView.findViewById(R.id.history_productPrice);
            timeStamp=itemView.findViewById(R.id.history_timeStamp);

        }
    }

}
