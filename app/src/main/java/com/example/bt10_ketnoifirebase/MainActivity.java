package com.example.bt10_ketnoifirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    private Button btlogin,btchuyensignup;
    private EditText edtuser,edtpass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        btlogin= findViewById(R.id.btlogin);
        btchuyensignup = findViewById(R.id.btchuyensignup);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btchuyensignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOpenSignUp();
            }
        });
    }
    public void doOpenSignUp(){
        Intent intent =new Intent(this, signup.class);
        startActivity(intent);
    }
    public void doOpenRv(){
        Intent intent =new Intent(this, RecyclerViewUsers.class);
        startActivity(intent);
    }

    public void login(){
        String email,pass;
        email = edtuser.getText().toString();
        pass = edtpass.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui long nhap email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui long nhap password!",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Dang nhap thanh cong!",Toast.LENGTH_SHORT).show();
                    doOpenRv();
                }else {
                    Toast.makeText(getApplicationContext(),"Dang nhap khong thanh cong!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}