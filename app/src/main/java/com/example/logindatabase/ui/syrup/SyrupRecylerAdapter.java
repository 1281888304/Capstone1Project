package com.example.logindatabase.ui.syrup;

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



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SyrupRecylerAdapter extends RecyclerView.Adapter<SyrupRecylerAdapter.ViewHolder>{

    private static final String tag = "RecylerView";
    private Context mContext;
    private ArrayList<Product> syrupList;
    private DatabaseReference myRef, numRef;

    private Context getmContext;
    //get user detail
    //get User detail
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    //child replace "1"
    private String userName="";



    public SyrupRecylerAdapter(Context mContext, ArrayList<Product> syrupList) {
        this.mContext = mContext;
        this.syrupList = syrupList;
    }

    @NonNull
    @Override
    public SyrupRecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.syrup_item, parent, false);

        return new SyrupRecylerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyrupRecylerAdapter.ViewHolder holder, int position) {

        String currentSyrup = syrupList.get(position).getProductTitle();
        final String[] numhelper = {""};

        //show the values
        holder.syrupName.setText(syrupList.get(position).getProductName());
        holder.syrupPrice.setText(syrupList.get(position).getProductPrice());

        holder.syrupTitle.setText(syrupList.get(position).getProductTitle());

        String checkNum=syrupList.get(position).getProductNum();
        if(checkNum.equals("0")){
            holder.syrupNum.setText("We are preparing");
        }else{
            holder.syrupNum.setText("Ready to order");
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
                .load(syrupList.get(position).getProductImage())
                .into(holder.syrupImage);

        //change to the child("username")
        numRef = FirebaseDatabase.getInstance().getReference()
                .child("Cart").child(userName);
        numRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(currentSyrup)) {
                    numhelper[0] ="0";
                    return;
                }else{

                    //new method
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Map<String,Object> map=(Map<String, Object>) ds.getValue();
                        Object title=map.get("cartProductTitle");
                        String currentTitle=String.valueOf(title);
                        if(currentTitle.equals(currentSyrup)){
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
            holder.syrupImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get intent
                    Intent intent = new Intent(v.getContext(), SyrupViewActivity.class);
                    intent.putExtra("productKey", currentSyrup);
                    intent.putExtra("productNum", numhelper[0]);
                    v.getContext().startActivities(new Intent[]{intent});
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return syrupList.size();
    }

    public void filteredList(ArrayList<Product> filterList) {
        syrupList=filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //widget
        ImageView syrupImage;
        TextView syrupName;
        TextView syrupPrice;
        TextView syrupNum;
        TextView syrupTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            syrupImage = itemView.findViewById(R.id.syrupImage);
            syrupName = itemView.findViewById(R.id.syrupName);
            syrupPrice = itemView.findViewById(R.id.syrupPrice);

            syrupNum = itemView.findViewById(R.id.syrupOrderNum);


            syrupTitle = itemView.findViewById(R.id.syrupTitle);

        }
    }
}
