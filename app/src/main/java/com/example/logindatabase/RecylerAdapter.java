package com.example.logindatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder>  {

    private static final String tag ="RecyclerView";
    private Context mContext;
    private ArrayList<OrderShow> orderList;

    public RecylerAdapter(Context mContext, ArrayList<OrderShow> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public RecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.companyView.setText(orderList.get(position).getCompany());
        holder.addressView.setText(orderList.get(position).getAddress());
        holder.statusView.setText(orderList.get(position).getStatus());
        holder.itemsView.setText(orderList.get(position).getItem());
        holder.quantityView.setText(orderList.get(position).getQuantity());
        holder.orderNumView.setText(orderList.get(position).getOrder_num());

    }



    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView companyView;
        TextView addressView;
        TextView itemsView;
        TextView orderNumView;
        TextView quantityView;
        TextView statusView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyView=itemView.findViewById(R.id.EditCompany);
            addressView=itemView.findViewById(R.id.EditAddress);
            itemsView=itemView.findViewById(R.id.EditItem);
            orderNumView=itemView.findViewById(R.id.EditOrderNum);
            quantityView=itemView.findViewById(R.id.EditQuantity);
            statusView=itemView.findViewById(R.id.EditStatus);
        }
    }
}
