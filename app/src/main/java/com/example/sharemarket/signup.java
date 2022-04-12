package com.example.sharemarket;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    EditText sFullName, sEmail, sPassword, sMobile;
    Button sSubmit;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    TextView alreadyAccount;
    FirebaseFirestore fStore;
    String userID;
    ImageView imageView10 , imageView11, imageView12;



    String user;
    private static final String USER = "user";
//    mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Signup");

        setContentView(R.layout.activity_signup);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        sFullName = findViewById(R.id.editTextSignUpName);
        sEmail = findViewById(R.id.editTextSignUpEmail);
        sPassword = findViewById(R.id.editTextSignUpPassword);
        sMobile = findViewById(R.id.editTextSignUpMobile);
        sSubmit = findViewById(R.id.buttonSignUPSubmit);
        alreadyAccount = findViewById(R.id.textViewAlreadyAccount);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        fAuth = FirebaseAuth.getInstance();
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);



        sFullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });



//        textInputLayout.editText?.text.toString().toDoubleOrNull()
//        mViewBinding.tlName.getEditText().getText().toString()

        sSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String email = sEmail.getText().toString().trim();
                String password = sPassword.getText().toString().trim();
                String fullName = sFullName.getText().toString();
                String mobile = sMobile.getText().toString();


//                String password = sPassword.getText().toString().trim();
//                String fullName = sFullName.getText().toString();
//                String mobile = sMobile.getText().toString();


             sFullName.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     imageView10.setVisibility(View.VISIBLE);
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }

             });
                sEmail.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     imageView11.setVisibility(View.VISIBLE);
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }

             });
             sMobile.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 }

                 @Override
                 public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     imageView12.setVisibility(View.VISIBLE);
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {

                 }

             });

//                registerUser(email,password);
//                Log.d("email", email);
                if (TextUtils.isEmpty(fullName)){
                    sFullName.setError("Name is required");
                    imageView10.setVisibility(View.GONE);
                }
                if (TextUtils.isEmpty(mobile)){
                    sMobile.setError("phone number is required");
                    imageView11.setVisibility(View.GONE);
                }
                if(TextUtils.isEmpty(email)){
                    sEmail.setError("Email is required");
                    imageView12.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    sPassword.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    sPassword.setError("Password must be >=6 characters");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

//                    String email = sEmail.getText().toString().trim();
//                    String password = sPassword.getText().toString().trim();
//                    String fullName = sFullName.getText().toString();
//                    String mobile = sMobile.getText().toString();
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(signup.this, "user created", Toast.LENGTH_SHORT).show();
//                    FirebaseUser user = fAuth.getCurrentUser();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName", fullName);
                            user.put("email", email);
                            user.put("mobile", mobile);
                            documentReference.set(user).addOnSuccessListener((OnSuccessListener) (Void) -> Log.d(TAG, "user profile is created for " + userID)).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailiure " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),login.class));
                        }
                        else{
                            Toast.makeText(signup.this, "Error ! " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }



        });


        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });


    }






        public void registerUser(String email, String password){

    }

}