package com.example.logindatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private TextView banner;
    private Button registerUser;
    private EditText editTextFullName, editTextPhone,
            editTextEmail,editTextPassword,editTextAddress;



    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner=(TextView) findViewById(R.id.logo1);
        banner.setOnClickListener(this);

        registerUser =(Button) findViewById(R.id.registeruser);
        registerUser.setOnClickListener(this);

        editTextFullName=(EditText) findViewById(R.id.fullName);
        editTextPhone=(EditText) findViewById(R.id.phone);
        editTextEmail=(EditText) findViewById(R.id.email);
        editTextPassword=(EditText) findViewById(R.id.password);
        editTextAddress=(EditText) findViewById(R.id.address);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logo1:
                startActivity(new Intent(this,LoginPage.class));
                break;
            case R.id.registeruser:
                registerUser();
                break;


        }
    }

    private void registerUser() {
        String email=editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();
        String fullName=editTextFullName.getText().toString().trim();
        String phoneNumber = editTextPhone.getText().toString().trim();
        String address=editTextAddress.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Please enter full name here");
            editTextFullName.requestFocus();
            return;
        }

        if(phoneNumber.isEmpty()){
            editTextPhone.setError("what's your phone number");
            editTextPhone.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Please tell me your email");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please tell us your real email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Min password is 6");
            editTextPassword.requestFocus();
            return;
        }
        if(address.isEmpty()){
            editTextAddress.setError("Please tell us your address");
            editTextAddress.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(fullName,phoneNumber,email,address);

                            //send it to database
//                            FirebaseDatabase database = FirebaseDatabase.getInstance();
//                            DatabaseReference myRef = database.getReference("message");

//                            myRef.setValue("Hello, World!");
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this,"User has been successful added",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(RegisterUser.this,"User has not be added error 1",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
//                        Toast.makeText(RegisterUser.this,"User has not be added error 2",Toast.LENGTH_LONG).show();
                    }
                });





    }
}