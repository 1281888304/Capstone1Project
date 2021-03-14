package com.example.logindatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProduct extends AppCompatActivity {

    private EditText editLiquid;
    private EditText editNumber;
    private EditText editTea;

    private Button add;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        add=(Button) findViewById(R.id.AddProduct);
        editLiquid=(EditText) findViewById(R.id.Liquid);
        editNumber=(EditText) findViewById(R.id.Number);
        editTea=(EditText) findViewById(R.id.TeaChoice);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance(); //Calling all database for both usres and products
                reference =rootNode.getReference("Shop");

                //get data from input
                String liquid=editLiquid.getText().toString();
                String number=editNumber.getText().toString();
                String teaLeaf=editTea.getText().toString();

                //get input object
                Producthelper helperClass= new Producthelper(liquid,teaLeaf,number);

                reference.child(number).setValue(helperClass);
            }
        });
    }
}