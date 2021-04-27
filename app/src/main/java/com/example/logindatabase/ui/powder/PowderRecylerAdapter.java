package com.example.logindatabase.ui.powder;

import androidx.recyclerview.widget.RecyclerView;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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

public class PowderRecylerAdapter extends RecyclerView.Adapter<PowderRecylerAdapter.ViewHolder> {

    private static final String tag = "RecylerView";
    private Context mContext;
    private ArrayList<Powders> powderList;
    private DatabaseReference myRef, numRef;

    private Context getmContext;
    //get user detail
    //get User detail
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    //child replace "1"
    private String userName="";

    public PowderRecylerAdapter(Context mContext, ArrayList<Powders> powderList) {
        this.mContext = mContext;
        this.powderList = powderList;
    }


    @NonNull
    @Override
    public PowderRecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //hold the view and put it on this template
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.powder_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String currentPowder = powderList.get(position).getPowderTitle();
        final String[] numhelper = {""};
        //load the data
        Glide.with(mContext)
                .load(powderList.get(position).getPowderImage())
                .into(holder.powderImage);
        holder.powderName.setText(powderList.get(position).getPowderName());
        holder.powderPrice.setText(powderList.get(position).getPowderPrice());

        holder.powderTitle.setText(powderList.get(position).getPowderTitle());

        //check whether user can purchase
        String checkNum=powderList.get(position).getPowderNum();
        if(checkNum.equals("0")){
            holder.powderNum.setText("We are preparing");
        }else{
            holder.powderNum.setText("Ready to order");
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

        //change to the child("username")
        numRef = FirebaseDatabase.getInstance().getReference()
                .child("Cart").child(userName);
        numRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(currentPowder)) {
                    numhelper[0] ="0";
                    return;
                }else{

                    //new method
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Map<String,Object> map=(Map<String, Object>) ds.getValue();
                        Object title=map.get("cartProductTitle");
                        String currentTitle=String.valueOf(title);
                        if(currentTitle.equals(currentPowder)){
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
            holder.powderImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get intent
                    Intent intent = new Intent(v.getContext(), PowderViewActivity.class);
                    intent.putExtra("productKey", currentPowder);
                    intent.putExtra("productNum", numhelper[0]);
                    v.getContext().startActivities(new Intent[]{intent});
                }
            });
        }




    }


    @Override
    public int getItemCount() {
        return powderList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        //widget
        ImageView powderImage;
        TextView powderName;
        TextView powderPrice;
        TextView powderNum;
        TextView powderTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            powderImage = itemView.findViewById(R.id.powderImage);
            powderName = itemView.findViewById(R.id.powderName);
            powderPrice = itemView.findViewById(R.id.powderPrice);

            powderNum = itemView.findViewById(R.id.powderNum);


            powderTitle = itemView.findViewById(R.id.powderTitle);

        }
    }

}
