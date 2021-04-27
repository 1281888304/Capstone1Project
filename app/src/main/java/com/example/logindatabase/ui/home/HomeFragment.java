package com.example.logindatabase.ui.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.logindatabase.FirstPage;
import com.example.logindatabase.MapsActivity;
import com.example.logindatabase.NavgationDrawer;
import com.example.logindatabase.ProfileActivity;
import com.example.logindatabase.R;
import com.example.logindatabase.Review;
import com.example.logindatabase.User;
import com.example.logindatabase.ui.shoppingCart.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    //get User detail
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    //child replace "1"
    private String userName="";

    private ImageView cart,contact,direct,logout;
    private TextView userWelcome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cart=(ImageView)view.findViewById(R.id.home_cart);
        contact=(ImageView)view.findViewById(R.id.home_contract);
        direct=(ImageView)view.findViewById(R.id.home_map);
        logout=(ImageView)view.findViewById(R.id.home_logout);
        userWelcome=(TextView)view.findViewById(R.id.home_username);

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
                    userWelcome.setText("Hello, "+userName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getClass().this,"Something wrong on our app",Toast.LENGTH_LONG).show();
            }
        });

        //4 imagebutton
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Review.class);
                startActivity(intent);
            }
        });

        direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DirectActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), FirstPage.class));
            }
        });



        return view;
    }
}