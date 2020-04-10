
package com.example.apploginui;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity
 {
    EditText username;
    EditText fullname;
    EditText inputPassword;
    EditText emailid;
    Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
     username=(EditText)findViewById(R.id.uname);
     fullname=(EditText)findViewById(R.id.fname);
     inputPassword=(EditText)findViewById(R.id.password);
     emailid=(EditText)findViewById(R.id.email);
     register=(Button)findViewById(R.id.gtlogin);

     register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String email = emailid.getText().toString().trim();
                 String password = inputPassword.getText().toString().trim();

                 if (TextUtils.isEmpty(email)) {
                     Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 if (TextUtils.isEmpty(password)) {
                     Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 if (password.length() < 6) {
                     Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                     return;
                 }





                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(signup.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    Intent it=new Intent(getApplicationContext(), login.class);
                                    startActivity(it);
                                }
                            }
                        });

            }
        });
    }

     boolean isEmail(EditText text) {
         CharSequence email = text.getText().toString();
         return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
     }



 }