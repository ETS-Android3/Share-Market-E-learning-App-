package com.example.sharemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    TextView signUp;
    EditText lEmail, lPassword;
    Button lButton;
    FirebaseAuth fAuth;
    TextView signUpAccount, forgotPassword;
    ImageView imageView4;





    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Login");

        fAuth = FirebaseAuth.getInstance();


        forgotPassword = findViewById(R.id.textViewForgotPassword);
        signUpAccount = findViewById(R.id.textViewAlreadyAccount);
        lEmail = findViewById(R.id.editTextEmaillogin);
        lPassword = findViewById(R.id.editTextPasswordlogin);
        lButton = findViewById(R.id.buttonSignUPSubmit);
        signUp = findViewById(R.id.textViewAlreadyAccount);
        imageView4 = findViewById(R.id.imageView4);




        if (fAuth.getCurrentUser() != null)

        {
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }



        lButton.setOnClickListener(new View.OnClickListener() {


                                       @Override

                                       public void onClick(View view) {
                                           String email = lEmail.getText().toString().trim();
                                           String password = lPassword.getText().toString().trim();

                                           lEmail.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                               }

                                               @Override
                                               public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                   imageView4.setVisibility(View.VISIBLE);
                                               }

                                               @Override
                                               public void afterTextChanged(Editable editable) {

                                               }

                                           });



                                           if (TextUtils.isEmpty(email)) {
                                               lEmail.setError("Email is required");
                                               imageView4.setVisibility(View.GONE);
                                               return;
                                           }
                                           if (TextUtils.isEmpty(password)) {
                                               lPassword.setError("Password is required");
                                               return;
                                           }
                                           if (password.length() < 6) {
                                               lPassword.setError("Password must be >=6 characters");
                                               return;
                                           }




                                           fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                               @Override
                                               public void onComplete(@NonNull Task<AuthResult> task) {


                                                   if (task.isSuccessful()) {
                                                       Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                                       startActivity(new Intent(getApplicationContext(), Home.class));
                                                   } else {
                                                       Toast.makeText(login.this, "Error ! " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                   }


                                               }


                                           });


                                       }



                                   }


        );



        signUpAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), signup.class));

            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resentMail = new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter your Email To received Reset Link.");
                passwordResetDialog.setView(resentMail);

                passwordResetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resentMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this, "Reset link sent to your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this, "Error! Reset Link is not Sent", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                passwordResetDialog.show();

            }
        });

    }

}