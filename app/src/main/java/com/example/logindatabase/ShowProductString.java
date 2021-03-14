package com.example.logindatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowProductString extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_string);

        final TextView liquidTextView=(TextView) findViewById(R.id.showLiquid);
        final TextView teaTextView=(TextView) findViewById(R.id.showTea);
        final TextView numberTextView=(TextView) findViewById(R.id.showNumber);

        rootNode=FirebaseDatabase.getInstance(); //Calling all database for both usres and products
        reference =rootNode.getReference("Shop").child("1");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Producthelper dataShopSimple=snapshot.getValue(Producthelper.class);
                String liquid=dataShopSimple.liquidSupport;
                String number=dataShopSimple.number;
                String tea=dataShopSimple.teaSupport;
                liquidTextView.setText(liquid);
                numberTextView.setText(number);
                teaTextView.setText(tea);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowProductString.this,"Something wrong on our app",Toast.LENGTH_LONG).show();
            }
        });
    }
}