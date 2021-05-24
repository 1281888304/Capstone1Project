package com.example.logindatabase.ui.allProduct;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.logindatabase.Product;
import com.example.logindatabase.R;

import com.example.logindatabase.User;

import com.example.logindatabase.ui.powder.PowderViewActivity;
import com.example.logindatabase.ui.syrup.SyrupViewActivity;
import com.example.logindatabase.ui.topping.ToppingViewActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class AllProductRecylerAdapter extends RecyclerView.Adapter<AllProductRecylerAdapter.ViewHolder> {

    private static final String tag = "RecylerView";
    private Context mContext;
    private ArrayList<Product> productList;
    private DatabaseReference myRef, numRef;

    private Context getmContext;
    //get user detail
    //get User detail
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    //child replace "1"
    private String userName="";

    public AllProductRecylerAdapter(Context mContext, ArrayList<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        return new AllProductRecylerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String currentProduct = productList.get(position).getProductTitle();
        final String[] numhelper = {""};

        //show the values
        holder.productName.setText(productList.get(position).getProductName());
        holder.productPrice.setText(productList.get(position).getProductPrice());

        holder.productTitle.setText(productList.get(position).getProductTitle());

        String checkNum=productList.get(position).getProductNum();
        if(checkNum.equals("0")){
            holder.productNum.setText("We are preparing");
        }else{
            holder.productNum.setText("Ready to order");
        }

        //get user detail
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile !=null){
                    String fullName=userProfile.fullName;
                    userName=fullName;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getClass().this,"Something wrong on our app",Toast.LENGTH_LONG).show();
            }
        });


        //image:Glide Library
        Glide.with(mContext)
                .load(productList.get(position).getProductImage())
                .into(holder.productImage);

        //change to the child("username")
        numRef = FirebaseDatabase.getInstance().getReference()
                .child("Cart").child(userName);
        numRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(currentProduct)) {
                    numhelper[0] ="0";
                    return;
                }else{

                    //new method
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Map<String,Object> map=(Map<String, Object>) ds.getValue();
                        Object title=map.get("cartProductTitle");
                        String currentTitle=String.valueOf(title);
                        if(currentTitle.equals(currentProduct)){
                            Object number=map.get("cartProductNum");
                            String num=String.valueOf(number);
                            numhelper[0] =num;
                            //stop the for loop
                            break;
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //bug on numberHelper //itemview.set
        if(!checkNum.equals("0")) {
            holder.productImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get intent
//                    Intent intent = new Intent(v.getContext(), AllProductViewActivity.class);
                    if(currentProduct.toLowerCase().startsWith("syrup")){
                        Intent intent = new Intent(v.getContext(), SyrupViewActivity.class);
                        intent.putExtra("productKey", currentProduct);
                        intent.putExtra("productNum", numhelper[0]);
                        v.getContext().startActivities(new Intent[]{intent});
                    }
                    else if(currentProduct.toLowerCase().startsWith("topping")){
                        Intent intent = new Intent(v.getContext(), ToppingViewActivity.class);
                        intent.putExtra("productKey", currentProduct);
                        intent.putExtra("productNum", numhelper[0]);
                        v.getContext().startActivities(new Intent[]{intent});
                    }
                    else if(currentProduct.toLowerCase().startsWith("powder")){
                        Intent intent = new Intent(v.getContext(), PowderViewActivity.class);
                        intent.putExtra("productKey", currentProduct);
                        intent.putExtra("productNum", numhelper[0]);
                        v.getContext().startActivities(new Intent[]{intent});
                    }

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filteredList(ArrayList<Product> filterList) {
        productList=filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //widget
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView productNum;
        TextView productTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);

            productNum = itemView.findViewById(R.id.productOrderNum);


            productTitle = itemView.findViewById(R.id.productTitle);

        }
    }
}
