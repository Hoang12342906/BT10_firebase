package com.example.bt10_ketnoifirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    private EditText edtuserdk, edtpassdk;
    private Button btcreate, btchuyensignin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth= FirebaseAuth.getInstance();
        edtuserdk = findViewById(R.id.edtuserdk);
        edtpassdk = findViewById(R.id.edtpassdk);
        btcreate =findViewById(R.id.btcreate);
        btchuyensignin = findViewById(R.id.btchuyensignin);
        btcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        btchuyensignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOpenSignActivity();
            }
        });
    }
    private void doOpenSignActivity(){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }


    public void signup(){
        String email,pass;
        email = edtuserdk.getText().toString();
        pass = edtpassdk.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui long nhap email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui long nhap password!",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Tao tai khoan thanh cong!",Toast.LENGTH_SHORT).show();
                    doOpenSignActivity();
                }else {
                    Toast.makeText(getApplicationContext(),"Tao tai khoan khong thanh cong!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}